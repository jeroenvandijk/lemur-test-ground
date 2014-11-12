(catch-args
  [:main-class "Main class that should be used"])

(defcluster cluster)

(defstep all)

(fire! cluster all)