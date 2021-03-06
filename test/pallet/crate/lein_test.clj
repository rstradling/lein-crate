(ns pallet.crate.lein-test
  (:require
   [clojure.test :refer :all]
   [pallet.action :refer [with-action-options]]
   [pallet.actions :refer [exec-checked-script remote-file]]
   [pallet.api :refer [plan-fn server-spec]]
   [pallet.build-actions :refer [build-actions]]
   [pallet.crate.lein :refer :all]
   [pallet.crate.java :refer [java]]
   [pallet.test-utils]))

(deftest install-lein-test
  (is (script-no-comment=
       (first (build-actions {:phase-context "install-lein"}
                (remote-file
                 "/usr/local/bin/lein"
                 :url (format *lein-url* "stable")
                 :insecure true
                 :no-versioning true
                 :mode "755")))
       (first  (build-actions {}
                 (lein-settings {})
                 (install-lein))))))

(deftest lein-test
  (is (script-no-comment=
       (first (build-actions {:phase-context "lein"}
                (exec-checked-script
                 "lein test"
                 ("/usr/local/bin/lein" test))))
       (first  (build-actions {}
                 (lein-settings {})
                 (lein :test))))))

(deftest leiningen-test
  (is (leiningen {})))

(def live-test-spec
  (server-spec
   :extends [(leiningen {}) (java {})]
   :phases {:test (plan-fn
                    (with-action-options {:script-prefix :no-sudo}
                      (lein "version")))}))
