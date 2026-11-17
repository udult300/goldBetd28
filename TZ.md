# Техническое задание (RU) — LIEVITO-28

Документ: **русский**. Продукт: **100% offline Android**. UI/контент: **100% итальянский**.

Codename: **LIEVITO-28**  
ORBIT_TAG: **g-d28**  
HEADER_LOCK: **Category 28 / Subtype D / Visual V5 / Nav N4 / Seed 28**

Матрица: **Cat28 World breads & baking techniques reference · Subtype D catalog + personal collection (local only) · Visual V5 Museum · Nav N4**

---

## 0) Жёсткие замки (Hard locks)

- **Папка проекта**: `/Users/cybercrot/AndroidStudioProjects/goldBetd28` (не менять)
- **Bundle / package / namespace**: `com.lievito.crosta.scaffale` (не менять)
- **Gradle rootProject.name**: `ScaffaleLievito` (не менять)
- **Application class**: `ScaffaleLievitoApp` (не менять)
- **Theme name**: `MuseumLievito` (не менять)
- **Codename**: `LIEVITO-28` (не менять)
- **ORBIT_TAG**: `g-d28` (не менять)
- **HEADER_LOCK**: `Category 28 / Subtype D / Visual V5 / Nav N4 / Seed 28` (не менять)
- **Seed**: 28 (не менять)

### 0.1. Store/launcher name (критично)

- **Store/launcher name**: `Scaffale Lievito`
- Разрешено **только**:
  - `res/values/strings.xml` → `app_name`
  - `AndroidManifest.xml` → `android:label="@string/app_name"`
- **Запрещено**: показывать `Scaffale Lievito` где-либо в Compose UI, текстах экранов, тулбарах, onboarding, настройках, about, уведомлениях.
- **Запрещено**: `stringResource(R.string.app_name)` в UI.

### 0.2. Anti-CLO / Offline only (критично)

Запрещено (и проверяется):

- `INTERNET` permission.
- WebView / JS bridge.
- OneSignal / GrowthBook / FCM / remote config / remote fetch.
- Любые сетевые вызовы (HTTP, sockets, remote images).
- Любые `GB_*` флаги/интеграции.

Разрешено:

- Только локальный контент (см. §5) + DataStore.
- Уведомления **локальные**, только после opt-in.

### 0.3. Backup запреты

- `android:allowBackup="false"` обязателен.
- Не добавлять `dataExtractionRules` / `fullBackupContent`.

---

## 1) Паспорт продукта

### 1.1. Суть

Оффлайн-справочник по **pani del mondo** и техникам выпечки/ферментации.  
Не рецептурник “по шагам в граммах”, не таймер/хабит, не магазин.

### 1.2. Голос (IT)

Ключевые слова: **lievito · crosta · impasto · scaffale**.  
Коротко. Спокойно. Обучающе.

Дисклеймер: **glutine** (на detail + settings/about).

Слоган (IT, смысл фикс):

- IT: **“Pani e lieviti, in scaffale.”**

---

## 2) IA и навигация (Nav N4)

**N4 = один хаб Bussola** + entry cards, без bottom bar.

Основные поверхности:

- **Bussola** (hub)
- **Catalogo**
- **Scaffale** (личная коллекция статусов)
- **Segnati** (favorites)
- **Impostazioni**
- **Informazioni** (About)
- **Dettaglio piatto**

Deep link только локально из уведомлений:

- Tap по **Pane del giorno** → открывает detail выбранного piatto.

---

## 3) Экраны (UI) — спецификация по состояниям

Везде текст **итальянский**. Ни одного app name в UI.

### 3.1. Onboarding (1 экран)

UI:

- Title: `Crosta, lievito, tempo.`
- Subtitle: `Riferimento educativo offline su pani e tecniche di panificazione.`
- Bullets:
  - `Nessun account. Nessun server.`
  - `Preferiti e stati locali.`
  - `Promemoria opzionali: “Pane del giorno” e “Scaffale pronto”.`
- Disclaimer:
  - `Attenzione: contiene glutine. Non è consulenza medica.`
- CTA: `Inizia`

State:

- Default: контент сразу.

Side effect:

- Ставит `onboarding_done = true`.

### 3.2. Bussola (Hub)

Блоки:

- Header: `Bussola`
- Counters:
  - `Totale` (число piatti)
  - `In scaffale`
  - `Studiata`
- Entry cards:
  - `Catalogo` (count)
  - `Scaffale` (in scaffale + studiata)
  - `Segnati` (favorites)
  - `Impostazioni`

Empty/error:

- Если контент пустой (не должен): `Errore: contenuti mancanti.` + `Riprova`.

### 3.3. Catalogo

UI:

- Search: `Cerca`
- Quick filters (минимум):
  - `Tecnica` (chips)
  - `Area` (chips)
- Sort:
  - `A–Z`
  - `Minuti`
- Counter: `{n} piatti`

Rows:

- Title + `area · tecnica · {minuti} min`
- Star toggle (Segnati)

Empty:

- `Nessun risultato. Prova a cancellare filtri o ricerca.`

### 3.4. Dettaglio piatto

UI:

- Title: `{piatto.titolo}`
- Meta: `{area} · {tecnica} · {minuti} min`
- Allergen line: `Contiene glutine.`
- Section `Punti chiave` (bullets)
- Body (2+ paragrafi)
- Controls:
  - Favorite toggle (star)
  - Stato: `Da studiare` / `In scaffale` / `Studiata`
- Footer disclaimer:
  - `Riferimento educativo. Non è consulenza medica.`

Error:

- `Scheda non trovata.`

### 3.5. Scaffale

Секции:

- `In scaffale`
- `Studiata`

Empty:

- Если обе пустые: `Vuoto. Apri una scheda e scegli uno stato.`

### 3.6. Segnati

List favorites.

Empty:

- `Vuoto. Apri una scheda e tocca la stella.`

### 3.7. Impostazioni

UI:

- Title: `Impostazioni`
- Section: `Promemoria locali (opzionali)`
- Toggle master: `Promemoria`
- Toggle: `Pane del giorno`
- Toggle: `Scaffale pronto`
- Info line: `LIEVITO-28 · Category 28 / Subtype D / Visual V5 / Nav N4 / Seed 28`
- Button: `Informazioni`
- Button: `Ripristina dati locali`

Reset:

- Очищает DataStore.
- Отменяет WorkManager задачy tips.

### 3.8. Informazioni (About)

UI:

- Title: `Informazioni`
- Section Build:
  - `LIEVITO-28 · Category 28 / Subtype D / Visual V5 / Nav N4 / Seed 28`
  - `ORBIT_TAG: g-d28`
  - `Versione: {VERSION_NAME} ({VERSION_CODE})`
- Section Offline:
  - `Tutto offline. Nessun tracking.`
- Section Allergy:
  - `Attenzione: contiene glutine.`

---

## 4) Дизайн (Visual V5 — MuseumLievito)

Цвета (фикс):

- Background `#F4EFE6`
- Surface `#FFFAF2`
- Text `#2B2620`
- Muted `#6B635A`
- Accent `#8C6A3F`

Правила:

- 1 accent.
- Минимум декоративных теней.
- Контраст обязателен.

---

## 5) Данные и офлайн-архитектура

### 5.1. Источник контента (single source of truth)

Контент **вшит в код**:

- `app/src/main/java/com/lievito/crosta/scaffale/data/LocalContent.kt`

Файл `app/src/main/assets/content/plates.json` **не используется** и удалён как stale scaffold.

### 5.2. Контент-модели (контракт)

- `Plate(id, titolo, sottotitolo, area, tecnica, minuti, punti[], testo[])`
- `GlossaryEntry(term, definition)`
- `Collection(id, titolo, descrizione, plateIds[])`

### 5.3. DataStore (Preferences)

Файл:

- `lievito28_prefs` (`PrefsStore.kt`)

Ключи:

- `favorites` (CSV ids)
- `status_{id}` ∈ `DA_STUDIARE|IN_SCAFFALE|STUDIATA`
- `tips_enabled: Boolean`
- `tips_pane_del_giorno: Boolean`
- `tips_scaffale_pronto: Boolean`

---

## 6) Контентная таксономия — минимум (факт)

Требования:

- **plates ≥ 78** (реальные IT тайтлы)
- **glossary ≥ 30**
- **collections ≥ 9**
- **tips ≥ 18**

Фактический контент:

- Plates: **78** (`LocalContent.piatti`)
- Glossary: **30** (`LocalContent.glossario`)
- Collections: **9** (`LocalContent.collezioni`)
- Tips: **18** (`LocalContent.tips`)

---

## 7) Уведомления (локальные, opt-in)

Реализация:

- `WorkManager` worker: `TipWorker`
- Канал: `channel_tips`
- Copy:
  - Title: `Pane del giorno` / `Scaffale pronto`
  - Body: `{microTip} + {plate.titolo}`

Запуск:

- Только если master tips enabled и тип включён.

---

## 8) BuildConfig поля (Gradle)

Должны существовать:

- `ORBIT_TAG = "g-d28"`
- `HEADER_LOCK = "Category 28 / Subtype D / Visual V5 / Nav N4 / Seed 28"`
- `CODENAME = "LIEVITO-28"`
- `PROJECT_SEED = 28`

---

## 9) Acceptance criteria

Must:

- Bundle = `com.lievito.crosta.scaffale`.
- `rootProject.name = ScaffaleLievito`.
- `ScaffaleLievitoApp` подключён в manifest.
- `allowBackup=false`.
- НЕТ `INTERNET` permission.
- НЕТ WebView / OneSignal / GrowthBook / remote fetch / `GB_*`.
- UI и контент **итальянский**.
- `Scaffale Lievito` встречается только в `strings.xml` (`app_name`) и `android:label`.
- Контент: plates=78, glossary=30, collections=9, tips=18.
- `./gradlew :app:assembleDebug` проходит.

---

## 10) Anti‑CLO чеклист

- [ ] Нет `android.permission.INTERNET`
- [ ] Нет `WebView`
- [ ] Нет OneSignal
- [ ] Нет GrowthBook / `GB_*`
- [ ] Нет remote config / fetch
- [ ] Только offline content + DataStore

---

*Grey Tih · Task GoldBet · `goldBetd28` · `g-d28`*

## Приложение A — Полный список schede (факт реализации)

Всего **78** schede (FULL ITALIAN):

1. Pane a lievitazione naturale
2. Biga italiana
3. Poolish
4. Autolisi
5. Baguette
6. Ciabatta
7. Focaccia
8. Pane di segale
9. Pumpernickel
10. Pane integrale
11. Naan
12. Roti / Chapati
13. Injera
14. Pita
15. Lavash
16. Bretzel
17. Bagel
18. Pane di patate
19. Brioche salata
20. Pane al vapore
21. Baozi (concetto)
22. Pane di mais
23. Arepa
24. Tortillas di farina
25. Schiacciata (stile toscano)
26. Panini morbidi
27. Tangzhong
28. Kneading minimo
29. Laminazione
30. Pieghe a libro
31. Pieghe coil
32. Formatura: tensione
33. Incisioni
34. Vapore in forno
35. Pietra / acciaio
36. Fermentazione in frigo
37. Pane rustico in cocotte
38. Pane piatto mediterraneo
39. Pane dolce semplice
40. Pasta madre: rinfresco
41. Lievito di birra: dosi
42. Sale e impasto
43. Idratazione: leggere la farina
44. Pirlatura
45. Stoccaggio pane
46. Pane senza impasto
47. Pane di grano duro
48. Pane nordico ai semi
49. Pane con prefermento misto
50. Crosta: colore e suono
51. Crumb: alveoli utili
52. Scelta farina
53. Pane in cassetta
54. Brioche dolce (base)
55. Pane all’olio
56. Piadina romagnola
57. Grissini torinesi
58. Pane carasau
59. Pane pretzel (variante morbida)
60. Lievito chimico: quando ha senso
61. Stretch & fold
62. Temperatura impasto
63. Autolisi lunga vs breve
64. Biga vs poolish (confronto)
65. Semola: impasto che beve
66. Pane ai cereali misti
67. Cottura in teglia
68. Lievitazione notturna
69. Pane al latte
70. Focaccia alta in teglia
71. Pane di farro (misto)
72. Avena (in miscela)
73. Lievito madre solida vs liquida
74. Rinfresco: 1:1:1 vs 1:2:2
75. Pasta di riporto
76. Scald (pregelatinizzato)
77. Malto diastatico
78. Temperatura interna di cottura

*Список сверен с runtime-контентом. Не менять bundle/matrix/ORBIT.*
