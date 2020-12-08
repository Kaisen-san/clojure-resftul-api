(ns clojure-restful-api.core
  (:gen-class)
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [io.pedestal.http.body-params :as body-params]))

(def contacts 
  (atom [{:name "Felipe" :phone "+55 11 99988-7766"}
         {:name "Vinícius" :phone "+55 11 98877-6655"}
         {:name "Mariana" :phone "+55 11 97766-5544"}
         {:name "Carlos" :phone "+55 11 96655-4433"}]))

(defn filter-contacts [params contacts]
  (filter (fn [contact] (= params (select-keys contact (keys params))))
          contacts))

(defn retrive-contacts-handler [request]
  (http/json-response (sort-by :name (filter-contacts (:params request {}) @contacts))))

(defn create-contact-handler [request]
  (let [new-contact (:json-params request)]
    (if (nil? (first (filter-contacts new-contact @contacts)))
      (do (swap! contacts conj new-contact)
          (-> new-contact
              http/json-response
              (assoc :status 201)))
      (-> {:error "Contato com mesmo nome já adicionado."}
          http/json-response
          (assoc :status 400)))))

(defn update-contact-handler [request]
  (let [[old-contact new-contact]
        [(first (filter-contacts (:params request) @contacts)) (:json-params request)]]
    (when old-contact
      (swap! contacts (fn [contact] (remove #(= (:name %) (:name old-contact)) contact))))
    (if (nil? (first (filter-contacts new-contact @contacts)))
      (do (swap! contacts conj new-contact)
          (-> new-contact
              http/json-response
              (assoc :status 201)))
      (-> {:error "Contato com mesmo nome já adicionado."}
          http/json-response
          (assoc :status 400)))))

(defn remove-contact-handler [request]
  (let [old-contact (first (filter-contacts (:params request) @contacts))]
    (if old-contact
      (do (swap! contacts (fn [contact] (remove #(= (:name %) (:name old-contact)) contact)))
          (http/json-response old-contact))
      (-> {:error "Nenhum contato encontrado com esse nome."}
          http/json-response
          (assoc :status 400)))))

(def routes
  (route/expand-routes
    #{["/contacts" :get retrive-contacts-handler :route-name :retrieve-contacts]
      ["/contacts" :post create-contact-handler :route-name :create-contact]
      ["/contacts" :put update-contact-handler :route-name :update-contact]
      ["/contacts" :delete remove-contact-handler :route-name :remove-contact]}))

(def pedestal-config
  (-> {::http/routes routes
       ::http/type :jetty
       ::http/join? false
       ::http/port 3000}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))

(defn -main
  "API desenvolvida utilizando o Pedestal"
  [& args]
  (println "Aplicação rodando na porta 3000..."))
