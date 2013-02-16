{:dev {:dependencies
       [[com.palletops/pallet "0.8.0-beta.1" :classifier "tests"]
        [ch.qos.logback/logback-classic "1.0.9"]
        [com.palletops/crates "0.8.0-SNAPSHOT"]
        [com.palletops/java-crate "0.8.0-beta.1"]]
       :plugins [[lein-set-version "0.3.0"]]
       :aliases {"live-test-up"
                 ["pallet" "up" "--phases" "settings,configure,test"]
                 "live-test-down" ["pallet" "down"]
                 "live-test" ["do" "live-test-up," "live-test-down"]}}
 :latest {:dependencies
          [[com.palletops/crates "0.8.0-SNAPSHOT"]
           [com.palletops/pallet "0.8.0-SNAPSHOT" :classifier "tests"]]}
 :doc {:dependencies [[codox-md "0.2.0"]]
       :codox {:writer codox-md.writer/write-docs
               :output-dir "doc/api/0.8"
               :src-dir-uri "https://github.com/pallet/java-crate/blob/develop"
               :src-linenum-anchor-prefix "L"}
       :aliases {"marg" ["marg" "-d" "doc/annotated/0.8/"]
                 "codox" ["doc"]
                 "doc" ["do" "codox," "marg"]}}
 :pallet {:source-paths ["src" "test"]
          :dependencies
          [[com.palletops/crates "0.8.0-SNAPSHOT"]
           [com.palletops/pallet "0.8.0-SNAPSHOT"]
           [com.palletops/pallet "0.8.0-SNAPSHOT" :classifier "tests"]]}
 :release
 {:set-version
  {:updates [{:path "README.md" :no-snapshot true}]}}}