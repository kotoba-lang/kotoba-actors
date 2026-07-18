(ns kotoba-actors.config
  "Resolution of actor-owned seed EDN from flat west sibling repositories.

  KOTOBA_ACTORS_ETZHAYYIM_ROOT may point at the directory containing the
  com-etzhayyim-* checkouts.  Otherwise the normal west layout is derived from
  this repository: orgs/kotoba-lang/kotoba-actors -> orgs/etzhayyim."
  (:require [clojure.java.io :as io]))

(def ^:private repository-root
  ;; io/resource yields an absolute file: URL for this source on the classpath,
  ;; independent of cwd — walk up to the 20-actors sibling root.
  (-> (io/resource "kotoba_actors/config.clj")
      io/file
      .getParentFile     ; kotoba_actors
      .getParentFile     ; src
      .getParentFile     ; kotoba-actors
      .getPath))

(def ^:private etzhayyim-root
  (or (System/getenv "KOTOBA_ACTORS_ETZHAYYIM_ROOT")
      (-> (io/file repository-root ".." ".." "etzhayyim")
          .getCanonicalPath)))

(defn actor-seed [actor relative-path]
  (str (io/file etzhayyim-root (str "com-etzhayyim-" actor) relative-path)))

(def kabuto-seed    (actor-seed "kabuto" "data/seed-public-companies.kotoba.edn"))
(def uchiwake-seed  (actor-seed "uchiwake" "data/seed-products.kotoba.edn"))
(def hokorobi-seed  (actor-seed "hokorobi" "data/seed-finrisk-graph.kotoba.edn"))
(def inochi-seed    (actor-seed "inochi" "data/seed-biosphere-graph.kotoba.edn"))
(def rasen-seed     (actor-seed "rasen" "data/seed-genome-graph.kotoba.edn"))
(def shiori-seed    (actor-seed "shiori" "data/seed-wellbecoming-graph.kotoba.edn"))
(def kadode-seed    (actor-seed "kadode" "data/seed-resignation-graph.kotoba.edn"))
(def tsugite-seed   (actor-seed "tsugite" "data/seed-peoples-graph.kotoba.edn"))
(def asobi-seed     (actor-seed "asobi" "data/seed-asobi-graph.kotoba.edn"))
(def hoshimori-seed (actor-seed "hoshimori" "data/seed-orbit-graph.kotoba.edn"))
(def tanemaki-seed  (actor-seed "tanemaki" "data/seed-stewardship-graph.kotoba.edn"))
