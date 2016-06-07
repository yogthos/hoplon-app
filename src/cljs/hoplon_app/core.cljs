(ns hoplon-app.core
  (:require
    [hoplon.core
     :as h
     :include-macros true]
    [javelin.core
     :refer [cell]
     :refer-macros [cell= dosync]]))

(def todo-items (cell ["foo" "bar"]))

(h/defelem todo-list [{:keys [title]}]
  (h/div
      (h/h4 (or title "TODO"))
      (h/ul
        (h/loop-tpl :bindings [todo todo-items]
                    (h/li todo)))))

(h/defelem add-todo []
  (let [new-item (cell "")]
    (h/div
      (h/input :type "text"
               :value new-item
               :change #(reset! new-item @%))
      (h/button :click #(dosync
                          (swap! todo-items conj @new-item)
                          (reset! new-item ""))
                (h/text "Add #~{(inc (count todo-items))}")))))

#_(h/defelem home []
  (h/div
    :id "app"
    (h/h3 "Welcome to Hoplon")))

#_(h/defelem home []
  (h/div
    :id "app"
    (h/h3 "Welcome to Hoplon")
    (h/p (cell= todo-items))))

#_(h/defelem home []
  (h/div
    :id "app"
    (h/h3 "Welcome to Hoplon")
    (h/p (cell= todo-items))
    (add-todo)))

(h/defelem home []
  (h/div
    :id "app"
    (h/h3 "Welcome to Hoplon")
    (todo-list {:title "TODO List"})
    (add-todo)))


(defn mount-root []
  (js/jQuery #(.replaceWith (js/jQuery "#app") (home))))

(defn init! []
  (mount-root))
