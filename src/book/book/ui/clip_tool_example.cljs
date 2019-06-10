(ns book.ui.clip-tool-example
  (:require [cljs.pprint :refer [cl-format]]
            [fulcro.ui.clip-tool :as ct]
            [fulcro.client.cards :refer [defcard-fulcro]]
            [fulcro.client.mutations :as m :refer [defmutation]]
            [com.fulcrologic.fulcro.dom :as dom]
            [com.fulcrologic.fulcro.components :as prim :refer [defsc]]))

(def minion-image "https://s-media-cache-ak0.pinimg.com/736x/34/c2/f5/34c2f59284fcdff709217e14df2250a0--film-minions-minions-images.jpg")

(defsc Root [this {:keys [ctool]}]
  {:initial-state
          (fn [p] {:ctool (prim/get-initial-state ct/ClipTool {:id        :clipper :aspect-ratio 0.5
                                                               :image-url minion-image})})
   :query [:ctool]}
  (dom/div
    (ct/ui-clip-tool (prim/computed ctool {:onChange (fn [props] (prim/set-state! this props))}))
    (ct/ui-preview-clip (merge (prim/get-state this) {:filename "minions.jpg"
                                                      :width    100 :height 200}))))
