(defproject demo-enfocus-pubsub-remote "0.1.0-SNAPSHOT"
  :description "Demo of Enfocus, PubSub and Remotes working together."
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [noir "1.3.0-beta8"]
                 [enfocus "1.0.0-alpha3" :exclusions [domina org.clojure/clojurescript]]
                 [domina "1.0.1-SNAPSHOT"]
                 [org.clojure/google-closure-library "0.0-1376-2"]
                 [org.clojure/google-closure-library-third-party "0.0-1376-2"]]
  :plugins [[lein-cljsbuild "0.2.7"]]
  :main dedb.server
  :cljsbuild {
    :builds {
      :dev
      {:source-path "src-cljs"
       :jar true
       :compiler {:output-to "resources/public/js/dedb-debug.js"
                  :optimizations :whitespace
                  :pretty-print true}}
      :prod
      {:source-path "src-cljs"
       :compiler {:output-to "resources/public/js/dedb.js"
                  :optimizations :advanced
                  :pretty-print false
                  :sourcemap true}}}})
