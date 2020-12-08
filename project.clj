(defproject clojure-restful-api "0.1.0-SNAPSHOT"
  :description "Restful API using Pedestal"
  :url "http://localhost:3000/contacts"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service "0.5.7"]
                 [io.pedestal/pedestal.route "0.5.7"]
                 [io.pedestal/pedestal.jetty "0.5.7"]
                 [org.slf4j/slf4j-simple "1.7.28"]]
  :jvm-opts["-Dclojure.server.myrepl={:port,4321,:accept,clojure.core.server/repl}"]
  :main ^:skip-aot clojure-restful-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies []
                   :source-paths ["dev"]}})
