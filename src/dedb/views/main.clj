(ns dedb.views.main
  (:require [noir.core :as noir]
            [hiccup.page :as page]
            [hiccup.element :as element]))

(noir/defpartial js-app
  [js]
  (list
   ;; avoid loading deps.js in whitespace mode
   (if (re-find #"debug" js) (element/javascript-tag "var CLOSURE_NO_DEPS = true;"))
   (page/include-js (str "/js/" js ".js"))
   (element/javascript-tag "dedb.app.run()")))

(noir/defpage "/" []
  (page/html5
   [:head
    [:title "Demo: Enfocus defsnippet bug."]]
   [:body
    [:h2 "defsnippet bug"]
    [:div
     [:p "Write something in either cases input, and press its Submit button."]
     [:p "The input will be removed from the dom and a fresh snippet used to re-add it."]
     [:p "The bug: the new input will have the value it had prior to removal."]
     [:p "This could only be there because the snippet's source html somehow got mutated."]]
    [:div#case-a
      [:h3 "Case A: em/listen inside defsnippet"]
      [:div.control]
      [:div.result]]
    [:div#case-b
      [:h3 "Case A: no em/listen inside defsnippet"]
      [:div.control]
      [:div.control2]
      [:div.result]]
    ;; remove "-debug" to use production mode js
    (js-app "dedb-debug")]))
