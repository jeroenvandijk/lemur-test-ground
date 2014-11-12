(defproject sample-job "0.1.0"
  :description "FIXME: write description"
  :repositories {"conjars"
                 {:url "http://conjars.org/repo"}}
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [cascalog/cascalog-core "2.1.1"]
                 [cascading/cascading-hadoop2-mr1 "2.5.2"]
                 [cascading/cascading-core "2.5.3"]]

  ;; Enforce Lein version to prevent issues like this https://github.com/technomancy/leiningen/issues/1717
  :min-lein-version "2.5.0"
  ;; IMPORTANT: main should be nil so Hadoop can take the 
  ;;            main-class argument.
  :main nil
  :aot [job.core]

  :pedantic :abort

  :profiles {:provided {:dependencies
                        [[org.apache.hadoop/hadoop-mapreduce-client-core "2.4.0"]
                         [org.apache.hadoop/hadoop-minicluster "2.4.0"]
                         [org.apache.hadoop/hadoop-common "2.4.0"]]}})
