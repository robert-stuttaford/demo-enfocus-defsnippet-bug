(ns dedb.app
  (:require [enfocus.core :as ef])
  (:require-macros [enfocus.macros :as em]))

(declare show-case-a-input! prepare-case-b!)

(defn ^:export run
  []
  (em/wait-for-load 
    (do 
      (show-case-a-input!)
      (prepare-case-b!))))

(em/defaction show-result!
  [case input]
  [case :.result] (em/content "Result: " (em/from (em/select [input]) (em/get-prop :value))))

(em/defaction clear-control!
  [case]
  [case :.control] (em/content ""))

(defn update-result-and-remove-control!
  [case input]
  (show-result! case input)
  (goog.Timer/callOnce (partial clear-control! case) 500))

(def t "templates/templates.html")

;; case A - em/listen in snippet

(declare do-case-A-submit!)

(em/defsnippet case-a-sn t [:#case-a] []
  [:button] (em/listen :click do-case-A-submit!))

(em/defaction show-case-a-input!
  []
  [:#case-a :.control] (em/content (case-a-sn)))

(defn do-case-A-submit!
  []
  (update-result-and-remove-control! :#case-a :input#caseA)
  (goog.Timer/callOnce show-case-a-input! 1000))

;; case B - no em/listen in snippet

(declare do-case-B-submit!)

(em/defsnippet case-b-input-sn t [:#case-b-input] [])
(em/defsnippet case-b-button-sn t [:#case-b-button] [])

(em/defaction show-case-b-input!
  []
  [:#case-b :.control] (em/content (case-b-input-sn)))

(em/defaction show-case-b-button!
  []
  [:#case-b :.control2] (em/content (case-b-button-sn)))

(em/defaction wire-case-b-button!
  []
  [:#case-b :button] (em/listen :click do-case-B-submit!))

(defn prepare-case-b!
  []
  (show-case-b-button!)
  (wire-case-b-button!)
  (show-case-b-input!))

(defn do-case-B-submit!
  []
  (update-result-and-remove-control! :#case-b :input#caseB)
  (goog.Timer/callOnce show-case-b-input! 1000))