(ns job.core
  (:require 
    [cascalog.logic.ops]
    [cascalog.cascading.io]
    [cascalog.api :refer :all])
  (:gen-class))

(defn -main [& _]
  (cascalog.cascading.io/with-fs-tmp [_ tmp-dir]
    (let [lazy-tap (cascalog.logic.ops/lazy-generator tmp-dir (map vector (range 10000)))]
      (?<- (stdout)
            [?a]
            (lazy-tap ?a)))))
