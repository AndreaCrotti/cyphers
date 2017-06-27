(ns cyphers.core)

(def start (int \a))
(def end (int \z))
(def start-upper (int \A))
(def end-upper (int \Z))
(def all-chars (concat (range start (inc end)) (range start-upper (inc end-upper))))

(def alpha-length (- end start))

#_(defn inc-by
  [chr shift-by]
  (let [idx (int chr)
        simple-sum (+ idx shift-by)]
    (char
     (if (> simple-sum end)
       (+ start
          (mod (+ (int chr) shift-by) end))
       simple-sum)))))

(defn uppercase?
  [chr]
  (contains? (set (range start-upper (inc end-upper))) (int chr)))

(defn inc-by
  [shift-by chr]
  (let [real-start (if (uppercase? chr) start-upper start)
        idx (int chr)
        offset (- idx real-start)
        to-shift (mod (+ offset shift-by) (inc alpha-length))]

    (char (+ real-start to-shift))))

(defn gen-map
  [shift-by]
  (into {}
        (for [c all-chars]
          [(char c) (inc-by shift-by c)])))

(defn subs
  [word subs-map]
  (clojure.string/join
   (map #(get subs-map %) word)))

(defn rot-by
  [word shift-by]
  (let [subs-map (gen-map shift-by)]
    (subs word subs-map)))

(def rot-by-custom subs)

(defn pad-to-message
  [cipher message]
  (let [msglength (count message)]
    (clojure.string/join
     (take msglength (apply concat (repeat cipher))))))

(defn many-maps
  []
  (into
   {}
   (for [n (range (inc alpha-length))]
     [(char (+ n start)) (gen-map n)])))
