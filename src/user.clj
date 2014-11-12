(ns user)

(defn get-absolute-path
  "Returns absolute path of files and targets of symlinks"
  [path]
  (.getCanonicalPath (clojure.java.io/file path)))

;; Adapted from https://github.com/jeroenvandijk/lemur/blob/feature/as-a-lib/src/main/clj/lemur/tool.clj
;; Needs cleaning
(require
 '[clojure.java.io :as io]
 '[com.climate.services.aws
   [common :as awscommon]
   [ec2 :as ec2]
   [s3 :as s3]
   [emr :as emr]]
 '[lemur [core :as l]
   [command-line :as cli]])


(defn launch-local []
  ;(l/in-context
  
  ;; TODO The goal is to put the following with in-context instead
  #_(l/in-context
   ;; ...
   )

  ;; FIXME running the following twice fails... with CompilerException java.io.FileNotFoundException: /sample-job-0.1.0-standalone.jar (Permission denied),
  ;; FIXME Until we use in-context, we need to reset the context (although that doesn't quite work given the exception above)...
  (reset! l/context l/default-context)
  (cli/add-command-spec* l/context cli/init-command-spec)
  (let [raw-args [
        "--main-class" "job.core"
        "--keypair" "foo"
        "--jar-src-path" (get-absolute-path "job.jar")]
      aws-creds (awscommon/aws-credential-discovery)
      jobdef-path (get-absolute-path "lemur-scripts/minimal.clj")
      profiles [":local"]]
  (l/add-profiles profiles)
  (l/context-set :jobdef-path jobdef-path)
  (l/context-set :raw-args raw-args)
  (l/context-set :command "local")
  (println "current context" l/context)
  ;; TODO lazy load credentials instead
  (binding [s3/*s3* (s3/s3 aws-creds)
            emr/*emr* (emr/emr aws-creds)
            ec2/*ec2* (ec2/ec2 aws-creds)]
    ;; TODO translate this to the new library functionality
    (l/execute-jobdef jobdef-path))))