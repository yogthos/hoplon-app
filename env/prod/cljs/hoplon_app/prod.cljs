(ns hoplon-app.prod
  (:require [hoplon-app.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
