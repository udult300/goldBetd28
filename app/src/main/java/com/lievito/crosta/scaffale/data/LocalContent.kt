package com.lievito.crosta.scaffale.data

import com.lievito.crosta.scaffale.model.Collection
import com.lievito.crosta.scaffale.model.GlossaryEntry
import com.lievito.crosta.scaffale.model.Plate

object LocalContent {
    val piatti: List<Plate> = buildList {
        fun p(
            id: Int,
            titolo: String,
            sottotitolo: String,
            area: String,
            tecnica: String,
            minuti: Int,
            punti: List<String>,
            testo: List<String>,
        ) = add(
            Plate(
                id = "p%03d".format(id),
                titolo = titolo,
                sottotitolo = sottotitolo,
                area = area,
                tecnica = tecnica,
                minuti = minuti,
                punti = punti,
                testo = testo,
            ),
        )

        p(1, "Pane a lievitazione naturale", "Base: pasta madre e tempi", "Europa", "fermentazione", 7,
            listOf("Acidità controllata", "Forza farina", "Gestione rinfreschi", "Cottura con vapore"),
            listOf(
                "La pasta madre è un ecosistema. Stessa farina, stessi orari, risultati più stabili.",
                "Non inseguire solo l’alveolatura: cerca profumo, elasticità e crosta.",
            ))
        p(2, "Biga italiana", "Pre-impasto asciutto", "Italia", "pre-impasto", 5,
            listOf("Idratazione bassa", "Aroma nocciola", "Maglia forte"),
            listOf(
                "Biga: acqua poca, farina tanto, tempo lungo. Il glutine si organizza da solo.",
                "Usala per pane croccante e sapore pulito senza acidità.",
            ))
        p(3, "Poolish", "Pre-impasto fluido", "Europa", "pre-impasto", 4,
            listOf("Idratazione 100%", "Dolcezza", "Gestione maturazione"),
            listOf(
                "Poolish: farina e acqua pari, poco lievito. Aumenta estensibilità.",
                "Ferma quando è al picco: superficie bombata e bolle fini.",
            ))
        p(4, "Autolisi", "Riposo farina+acqua", "Francia", "tecnica", 3,
            listOf("Impasto meno stress", "Assorbimento migliore", "Meno ossidazione"),
            listOf(
                "Autolisi: farina e acqua riposano prima di sale e lievito.",
                "Risultato: impasto più docile e sapore più pieno.",
            ))
        p(5, "Baguette", "Crosta sottile, mollica leggera", "Francia", "formatura", 6,
            listOf("Pieghe mirate", "Tensione in superficie", "Tagli obliqui"),
            listOf(
                "La baguette vive di precisione: puntata corta, appretto controllato.",
                "Tagli rapidi e sicuri. Vapore forte all’inizio, poi asciugatura.",
            ))
        p(6, "Ciabatta", "Impasto molto idratato", "Italia", "idratazione", 6,
            listOf("Mani bagnate", "Pieghe a coil", "Cottura ben spinta"),
            listOf(
                "La ciabatta non si lavora: si accompagna. Pieghe e riposi fanno il resto.",
                "Forma poco, non schiacciare i gas. Tepsia calda, vapore e coraggio.",
            ))
        p(7, "Focaccia", "Olio, sale, bolle grandi", "Italia", "teglia", 5,
            listOf("Impasto morbido", "Riposo lungo", "Salamoia leggera"),
            listOf(
                "Focaccia: impasto soffice, tanta aria. L’olio protegge e profuma.",
                "Pressa con le dita senza strappare. Sale dopo la prima spinta in forno.",
            ))
        p(8, "Pane di segale", "Densità e aroma", "Nord Europa", "farine", 6,
            listOf("Gelatinizzazione", "Acidità utile", "Taglio dopo riposo"),
            listOf(
                "La segale non fa rete come il grano. Serve acidità e struttura diversa.",
                "Raffredda bene prima di tagliare: la mollica si assesta.",
            ))
        p(9, "Pumpernickel", "Cottura lenta", "Germania", "cottura", 5,
            listOf("Forno basso", "Umidità", "Sapore tostato"),
            listOf(
                "Pumpernickel: dolce e scuro, nasce da cotture lunghissime e umide.",
                "Qui studi la pazienza: tempo è ingrediente.",
            ))
        p(10, "Pane integrale", "Crusca e gestione acqua", "Ovunque", "farine", 4,
            listOf("Idratazione extra", "Autolisi utile", "Fermentazione più rapida"),
            listOf(
                "La crusca taglia la maglia. Compensa con acqua e riposi.",
                "Cerca morbidezza: meglio leggermente sottovitato che collassato.",
            ))
        p(11, "Naan", "Tandoor e morbidezza", "Asia del Sud", "cottura", 4,
            listOf("Calore altissimo", "Impasto elastico", "Pennellata finale"),
            listOf(
                "Naan: pane veloce. Il calore fa gonfiare e macchiare in pochi secondi.",
                "In padella rovente: gira appena compaiono le bolle grandi.",
            ))
        p(12, "Roti / Chapati", "Sfoglia fine", "Asia del Sud", "laminazione", 4,
            listOf("Impasto asciutto", "Riposo", "Cottura a secco"),
            listOf(
                "Roti: farina e acqua, niente fronzoli. La mano conta più della ricetta.",
                "Cerca rotondità e spessore costante. Gonfiaggio = buona stesura.",
            ))
        p(13, "Injera", "Fermentazione acida", "Africa orientale", "fermentazione", 6,
            listOf("Impasto fluido", "Aromi lattici", "Cottura tipo crêpe"),
            listOf(
                "Injera: pastella fermentata. L’acidità è il suo carattere.",
                "Cottura su piastra: una sola faccia, copri per creare occhiature.",
            ))
        p(14, "Pita", "Tasca interna", "Medio Oriente", "cottura", 4,
            listOf("Forno molto caldo", "Dischi sottili", "Vapore interno"),
            listOf(
                "La pita si gonfia se la superficie sigilla subito. Calore e pietra aiutano.",
                "Stendi uniforme. Non asciugare i dischi in attesa: coprili.",
            ))
        p(15, "Lavash", "Pane sottile", "Caucaso", "stesa", 4,
            listOf("Stesura estrema", "Cottura rapida", "Conservazione in telo"),
            listOf(
                "Lavash: più sottile, più buono. Serve impasto rilassato e mani leggere.",
                "Cuoci veloce. Per morbidezza: impila e avvolgi in un telo.",
            ))
        p(16, "Bretzel", "Bagno alcalino", "Germania", "bollitura", 6,
            listOf("Liscivia/bicarbonato", "Lucidatura", "Taglio pancia"),
            listOf(
                "Bretzel: la crosta nasce dal bagno alcalino. Colore e sapore cambiano.",
                "Fai forma tesa, bagno breve, poi sale grosso e forno forte.",
            ))
        p(17, "Bagel", "Bollito e poi forno", "Nord America", "bollitura", 6,
            listOf("Impasto tenace", "Bollitura con malto", "Mollica fitta"),
            listOf(
                "Bagel: mastica e soddisfa. Bollitura = crosta lucida e set.",
                "Fermentazione fredda migliora aroma e gestibilità.",
            ))
        p(18, "Pane di patate", "Morbidezza lunga", "Europa", "arricchito", 4,
            listOf("Amidi extra", "Idratazione percepita", "Mollica soffice"),
            listOf(
                "Le patate portano amido e acqua legata. Il pane resta morbido.",
                "Attento al sale: la patata amplifica sapidità.",
            ))
        p(19, "Brioche salata", "Uova e burro", "Francia", "arricchito", 6,
            listOf("Impasto freddo", "Burro in più fasi", "Lievitazione lenta"),
            listOf(
                "Arricchiti: il grasso rallenta. Lavora freddo e con pazienza.",
                "Quando la maglia c’è, il burro entra senza strappare.",
            ))
        p(20, "Pane al vapore", "Soffice e bianco", "Asia orientale", "cottura", 4,
            listOf("Impasto liscio", "Appretto breve", "Vapore costante"),
            listOf(
                "Cottura a vapore: niente crosta, solo nuvola.",
                "Non aprire il coperchio a metà: collassa facilmente.",
            ))
        p(21, "Baozi (concetto)", "Ripieno e piega", "Asia orientale", "formatura", 5,
            listOf("Chiusura stretta", "Ripieno non acquoso", "Cottura a vapore"),
            listOf(
                "Baozi: il pane abbraccia il ripieno. La piega è tecnica.",
                "Bilancia umidità: troppo liquido rompe e macchia la base.",
            ))
        p(22, "Pane di mais", "Dolcezza e crumble", "Americhe", "farine", 4,
            listOf("Mais non fa rete", "Leganti", "Cottura in teglia"),
            listOf(
                "Il mais dà profumo, ma non struttura. Serve supporto: grano o uova.",
                "Cottura in teglia: bordi tostati, centro umido.",
            ))
        p(23, "Arepa", "Farina precotta", "America Latina", "piastra", 4,
            listOf("Impasto immediato", "Cottura doppia", "Farcitura"),
            listOf(
                "Arepa: impasto e via. Prima piastra, poi forno per asciugare.",
                "Taglia e farcisci: ottima per “in scaffale” come base pranzo.",
            ))
        p(24, "Tortillas di farina", "Sfoglia elastica", "Messico", "stesa", 4,
            listOf("Grasso nell’impasto", "Riposo", "Cottura a secco"),
            listOf(
                "Tortilla di farina: morbida, pieghevole. Riposo = stesura facile.",
                "Piastra calda: macchie scure, niente crosta dura.",
            ))
        p(25, "Schiacciata (stile toscano)", "Sale e olio", "Italia", "teglia", 4,
            listOf("Bolle irregolari", "Pressione leggera", "Crosta dorata"),
            listOf(
                "Schiacciata: più sottile della focaccia. La crosta deve cantare.",
                "Meglio poco topping: fai parlare farina e olio.",
            ))
        p(26, "Panini morbidi", "Crescita regolare", "Ovunque", "arricchito", 4,
            listOf("Tangzhong opzionale", "Pirlatura", "Cottura dolce"),
            listOf(
                "Panini: tensione perfetta e umidità controllata.",
                "Cuoci non troppo: colore chiaro = morbidezza lunga.",
            ))
        p(27, "Tangzhong", "Gel di farina", "Asia", "tecnica", 4,
            listOf("Amido gelatinizzato", "Sofficità", "Shelf-life"),
            listOf(
                "Tangzhong: cuoci farina+acqua fino a gel. Poi impasto classico.",
                "Risultato: mollica setosa e giorni extra di morbidezza.",
            ))
        p(28, "Kneading minimo", "Pieghe vs impasto", "Moderno", "tecnica", 3,
            listOf("Meno ossidazione", "Pieghe programmate", "Struttura pulita"),
            listOf(
                "Impastare meno: profumo migliore e gestione più semplice.",
                "Pieghe ogni 20–30 min: la maglia cresce senza stress.",
            ))
        p(29, "Laminazione", "Strati e aria", "Moderno", "tecnica", 5,
            listOf("Stendi sottile", "Incorpora aria", "Riposo dopo"),
            listOf(
                "Laminazione: stendi l’impasto come un foglio, poi ripiega.",
                "Ottima per impasti idratati: struttura e leggerezza.",
            ))
        p(30, "Pieghe a libro", "Struttura ordinata", "Ovunque", "tecnica", 3,
            listOf("Tensione controllata", "Riposi brevi", "Allineamento glutine"),
            listOf(
                "Pieghe a libro: semplice e potente.",
                "Non schiacciare: accompagna, poi lascia lavorare il tempo.",
            ))
        p(31, "Pieghe coil", "Impasti molto morbidi", "Moderno", "tecnica", 3,
            listOf("Mani bagnate", "Solleva e ripiega", "Delicatezza"),
            listOf(
                "Coil fold: solleva l’impasto e lascialo ripiegare su se stesso.",
                "Ottimo quando la ciotola è la tua migliore attrezzatura.",
            ))
        p(32, "Formatura: tensione", "Pelle esterna", "Ovunque", "formatura", 4,
            listOf("Tensione senza strappi", "Sigillo sotto", "Farina minima"),
            listOf(
                "La forma è una promessa: se la pelle tiene, il pane cresce bene.",
                "Troppa farina = scivola. Troppa forza = strappa.",
            ))
        p(33, "Incisioni", "Tagli che guidano", "Ovunque", "cottura", 4,
            listOf("Lama affilata", "Angolo corretto", "Profondità"),
            listOf(
                "Incidere è disegnare la crescita. Tagli lenti fanno danni.",
                "Per orecchio: angolo basso e gesto deciso.",
            ))
        p(34, "Vapore in forno", "Crosta sottile", "Ovunque", "cottura", 3,
            listOf("Ritarda la crosta", "Massima espansione", "Asciugatura finale"),
            listOf(
                "Il vapore tiene morbida la superficie: il pane può espandersi.",
                "Dopo 10–15 minuti: via vapore, serve asciugare la crosta.",
            ))
        p(35, "Pietra / acciaio", "Spinta dal basso", "Ovunque", "cottura", 3,
            listOf("Accumulo calore", "Base croccante", "Stabilità"),
            listOf(
                "Pietra e acciaio migliorano l’energia iniziale.",
                "Preriscalda bene: almeno 40–60 minuti se puoi.",
            ))
        p(36, "Fermentazione in frigo", "Aroma e controllo", "Ovunque", "fermentazione", 4,
            listOf("Programmazione", "Aroma più complesso", "Gestione appretto"),
            listOf(
                "Il freddo rallenta e regala gusto. È il tuo timer naturale.",
                "Non esagerare: impasto troppo acido perde forza.",
            ))
        p(37, "Pane rustico in cocotte", "Forno domestico friendly", "Ovunque", "cottura", 4,
            listOf("Vapore auto", "Crosticina uniforme", "Tagli semplici"),
            listOf(
                "Cocotte: microforno. All’inizio è umida, poi asciuga e colora.",
                "Attento alle mani: ghisa rovente, sempre guanti.",
            ))
        p(38, "Pane piatto mediterraneo", "Senza teglia", "Mediterraneo", "piastra", 3,
            listOf("Stesura media", "Cottura rapida", "Flessibilità"),
            listOf(
                "Piadina/pane piatto: base universale per “scaffale”.",
                "Piastra calda e riposo breve fanno il lavoro.",
            ))
        p(39, "Pane dolce semplice", "Zucchero e lievito", "Ovunque", "arricchito", 4,
            listOf("Zucchero rallenta", "Impasto freddo", "Aromi"),
            listOf(
                "Zucchero = più tempo. Non spingere con troppo lievito.",
                "Aromi: vaniglia, scorza, spezie. Poco ma pulito.",
            ))
        p(40, "Pasta madre: rinfresco", "Routine stabile", "Ovunque", "fermentazione", 4,
            listOf("Rapporto", "Temperatura", "Segnali di picco"),
            listOf(
                "Rinfresco: farina+acqua danno carburante. Il rapporto decide la velocità.",
                "Segnali: volume, profumo, bolle. Scrivi i tuoi tempi.",
            ))
        p(41, "Lievito di birra: dosi", "Velocità e sapore", "Ovunque", "fermentazione", 3,
            listOf("Poco lievito", "Tempo lungo", "Aroma migliore"),
            listOf(
                "Più lievito non è sempre meglio. Poco + tempo = gusto.",
                "Se hai fretta: alza temperatura, non solo grammi.",
            ))
        p(42, "Sale e impasto", "Quando e quanto", "Ovunque", "tecnica", 3,
            listOf("Rinforza maglia", "Rallenta", "Distribuzione uniforme"),
            listOf(
                "Il sale rafforza e rallenta. Mettilo dopo autolisi se la usi.",
                "Scioglilo bene: grumi = macchie e strappi.",
            ))
        p(43, "Idratazione: leggere la farina", "Non è un numero fisso", "Ovunque", "idratazione", 4,
            listOf("Assorbimento", "Temperatura", "Farinatura minima"),
            listOf(
                "Idratazione è feeling: farina, clima, mano.",
                "Cerca coerenza: stessa ciotola, stessi gesti, note rapide.",
            ))
        p(44, "Pirlatura", "Sfera tesa", "Italia", "formatura", 3,
            listOf("Tensione", "Superficie liscia", "Base sigillata"),
            listOf(
                "Pirlare: rendere tondo e teso. Serve poco attrito, tanta precisione.",
                "Stop quando la pelle canta: oltre, strappi.",
            ))
        p(45, "Stoccaggio pane", "Crosta vs morbidezza", "Ovunque", "conservazione", 3,
            listOf("Tessuto vs plastica", "Ravviva in forno", "Congelamento"),
            listOf(
                "Croccante: carta o tessuto. Morbido: chiusura ermetica breve.",
                "Congela a fette: scongela rapido, tosta meglio.",
            ))
        p(46, "Pane senza impasto", "Metodo notturno", "Ovunque", "fermentazione", 3,
            listOf("Tempo lungo", "Pieghe minime", "Cottura in cocotte"),
            listOf(
                "No-knead: impasto grezzo, notte, poi forma gentile.",
                "Ottimo per iniziare: ti insegna a leggere l’impasto.",
            ))
        p(47, "Pane di grano duro", "Giallo e profumato", "Italia", "farine", 4,
            listOf("Assorbe più", "Mollica compatta", "Crosta spessa"),
            listOf(
                "Semola rimacinata: profumo unico. Serve acqua e riposo.",
                "Cuoci bene: il colore deve diventare ambrato.",
            ))
        p(48, "Pane nordico ai semi", "Semi tostati", "Nord Europa", "arricchito", 4,
            listOf("Semi ammollati", "Tostatura", "Taglio freddo"),
            listOf(
                "Semi: sapore e texture. Ammollo evita che rubino acqua.",
                "Pane ottimo a fette sottili: aspetta il raffreddamento completo.",
            ))
        p(49, "Pane con prefermento misto", "Biga + frigo", "Moderno", "fermentazione", 5,
            listOf("Aroma profondo", "Programmazione", "Struttura"),
            listOf(
                "Combina pre-impasto e freddo per gusto senza complicazioni.",
                "Segna tempi reali: il tuo forno è parte della ricetta.",
            ))
        p(50, "Crosta: colore e suono", "Quando è davvero cotto", "Ovunque", "cottura", 3,
            listOf("Tostatura", "Umidità interna", "Raffreddamento"),
            listOf(
                "Pane cotto: crosta color nocciola, suono vuoto, base asciutta.",
                "Raffredda su griglia: la mollica finisce di stabilizzarsi.",
            ))
        p(51, "Crumb: alveoli utili", "Non solo Instagram", "Ovunque", "analisi", 3,
            listOf("Uniformità", "Elasticità", "Segni di sovra/ sotto"),
            listOf(
                "Alveoli grandi non sempre = buono. Cerca equilibrio e consistenza.",
                "Sotto: denso e strappo. Sopra: collasso e acidità pungente.",
            ))
        p(52, "Scelta farina", "Forza e proteine", "Ovunque", "ingredienti", 4,
            listOf("W e P/L", "Ceneri", "Uso per stile"),
            listOf(
                "Farina: leggi etichetta e guarda comportamento. Il numero non basta.",
                "Accoppia: forte per lunghe, più debole per pane tenero e veloce.",
            ))
        p(53, "Pane in cassetta", "Morbidezza e fette regolari", "Europa", "teglia", 5,
            listOf("Struttura fine", "Umidità controllata", "Cottura uniforme"),
            listOf(
                "Pane in cassetta: l’obiettivo è una mollica fine e fette che non si strappano.",
                "Stampo e chiusura aiutano a trattenere vapore: attenzione a non sottovitare.",
                "Lascia raffreddare bene prima di tagliare: la fetta ‘si assesta’.",
            ))
        p(54, "Brioche dolce (base)", "Burro, uova, tempo", "Francia", "arricchito", 6,
            listOf("Impasto freddo", "Burro in più fasi", "Lievitazione lunga"),
            listOf(
                "Brioche: non è solo dolcezza. È struttura + burro incorporato senza strappi.",
                "Lavora freddo: il burro deve entrare quando la maglia è già presente.",
                "Se perdi forza, fermati e riposa: tempo batte frenesia.",
            ))
        p(55, "Pane all’olio", "Morbido, profumato", "Mediterraneo", "arricchito", 4,
            listOf("Olio = elasticità", "Fermentazione calma", "Crosta dorata"),
            listOf(
                "L’olio rende la mollica più tenera e rallenta un po’ la lievitazione.",
                "Meglio poco lievito e più tempo: aroma più pulito e gestione facile.",
                "Cottura dolce: colore nocciola, non bruciato.",
            ))
        p(56, "Piadina romagnola", "Sfoglia semplice e rapida", "Italia", "piastra", 4,
            listOf("Impasto rilassato", "Stesura uniforme", "Cottura a secco"),
            listOf(
                "Piadina: poche variabili, ma la mano conta. Riposo = stesura più facile.",
                "Piastra calda: macchie scure sì, crosta dura no.",
                "Per morbidezza: impila e copri con un telo mentre cuoci.",
            ))
        p(57, "Grissini torinesi", "Croccanti e sottili", "Italia", "formatura", 5,
            listOf("Impasto asciutto", "Allungamento", "Cottura ben spinta"),
            listOf(
                "Grissini: più sono sottili, più richiedono controllo di tensione e asciugatura.",
                "Allunga senza strappare: se resiste, riposa 5 minuti e riprova.",
                "Cottura: meglio asciugare bene che colorare troppo.",
            ))
        p(58, "Pane carasau", "Sfoglia e tostatura", "Italia", "cottura", 6,
            listOf("Sfoglia sottile", "Gonfiaggio", "Seconda cottura"),
            listOf(
                "Carasau: prima cottura per gonfiare, seconda per asciugare e rendere croccante.",
                "Il segreto è la stesura: spessore uniforme o il disco non si apre bene.",
                "È un pane ‘di conservazione’: pensa a umidità e croccantezza.",
            ))
        p(59, "Pane pretzel (variante morbida)", "Crosta lucida e sapore deciso", "Germania", "bollitura", 6,
            listOf("Bagno alcalino", "Crosticina", "Taglio netto"),
            listOf(
                "Versione morbida: stessa idea del bretzel, ma forma da panino e mollica più soffice.",
                "Bagno breve: lucida la superficie e cambia sapore.",
                "Taglio rapido e forno forte: espansione controllata.",
            ))
        p(60, "Lievito chimico: quando ha senso", "Baking powder senza miti", "Ovunque", "ingredienti", 3,
            listOf("Reazione rapida", "Non è fermentazione", "Texture diversa"),
            listOf(
                "Lievito chimico = reazione, non maturazione. Ottimo per impasti veloci, non per aroma profondo.",
                "Non aspettarti alveoli da pane: la struttura è più ‘torta’, più uniforme.",
                "Leggi la data e dosa con prudenza: troppo = sapore sgradevole.",
            ))
        p(61, "Stretch & fold", "Rinforzo gentile", "Moderno", "tecnica", 3,
            listOf("Pieghe ritmate", "Meno impasto", "Struttura pulita"),
            listOf(
                "Stretch & fold: tiri e ripieghi. È un modo semplice per costruire maglia senza stress.",
                "Ritmo tipico: ogni 20–30 minuti all’inizio della puntata.",
                "Fermati quando l’impasto regge forma: oltre, fai solo ossidazione.",
            ))
        p(62, "Temperatura impasto", "Il termometro che non hai", "Ovunque", "analisi", 4,
            listOf("Velocità fermentazione", "Forza percepita", "Ripetibilità"),
            listOf(
                "Temperatura impasto decide tempi reali. Caldo accelera, freddo rallenta.",
                "Se un giorno ‘non torna’, spesso è solo più caldo o più freddo del solito.",
                "Segna temperatura stanza e impasto: ti salva più di una ricetta.",
            ))
        p(63, "Autolisi lunga vs breve", "Quando aiuta davvero", "Francia", "tecnica", 4,
            listOf("Assorbimento", "Elasticità", "Meno stress"),
            listOf(
                "Autolisi breve: rende impasto più docile. Autolisi lunga: cambia ancora di più, ma non sempre serve.",
                "Se l’impasto perde tenuta, stai esagerando per la tua farina.",
                "Regola: fai test piccoli e annota: ogni farina risponde diversa.",
            ))
        p(64, "Biga vs poolish (confronto)", "Due pre-impasti, due caratteri", "Italia/Europa", "pre-impasto", 5,
            listOf("Biga = forza", "Poolish = estensibilità", "Gestione picco"),
            listOf(
                "Biga: asciutta, tende a dare struttura e profumo ‘pulito’.",
                "Poolish: fluida, aiuta estensibilità e una mollica più ‘aperta’ a parità di impasto.",
                "In entrambi: fermati al picco, non oltre. Segni > orologio.",
            ))
        p(65, "Semola: impasto che beve", "Grano duro senza sorpresa", "Italia", "farine", 4,
            listOf("Assorbe molto", "Riposi utili", "Cottura ambrata"),
            listOf(
                "La semola rimacinata può chiedere più acqua e più riposo: non forzarla con impasto duro.",
                "Riposi brevi tra pieghe rendono l’impasto più liscio e gestibile.",
                "Cottura: colore ambrato = sapore. Non avere paura di spingere il forno.",
            ))
        p(66, "Pane ai cereali misti", "Profumo e texture", "Europa", "ingredienti", 4,
            listOf("Ammollo", "Bilancia acqua", "Taglio a freddo"),
            listOf(
                "Cereali e fiocchi rubano acqua: ammollo = mollica più morbida e prevedibile.",
                "Non caricare troppo: meglio pochi ingredienti capiti bene.",
                "Aspetta il raffreddamento completo: il taglio migliora davvero.",
            ))
        p(67, "Cottura in teglia", "Base croccante, bordo dorato", "Ovunque", "teglia", 4,
            listOf("Pre-riscaldo", "Olio/antiaderenza", "Gestione vapore"),
            listOf(
                "In teglia conta la trasmissione di calore: metallo caldo = base più stabile.",
                "Olio aiuta doratura e distacco, ma non deve friggere: dosalo.",
                "Apri forno a fine cottura per asciugare: la crosta resta migliore.",
            ))
        p(68, "Lievitazione notturna", "Programmare senza ansia", "Ovunque", "fermentazione", 4,
            listOf("Freddo = timer", "Aroma", "Gestione appretto"),
            listOf(
                "Notte in frigo: ottima per gusto e planning. Ma serve impasto già ‘in controllo’.",
                "Se al mattino è collassato, eri troppo avanti prima del freddo.",
                "Annota tempi reali: ogni frigo e stanza è diversa.",
            ))
        p(69, "Pane al latte", "Morbido e neutro", "Asia/Europa", "arricchito", 4,
            listOf("Zucchero rallenta", "Cottura dolce", "Raffreddamento"),
            listOf(
                "Pane al latte: struttura fine e gusto pulito. Il latte cambia colore e morbidezza.",
                "Non cuocere troppo: meglio chiaro per mantenere soffice.",
                "Conserva chiuso: qui la crosta non è protagonista.",
            ))
        p(70, "Focaccia alta in teglia", "Alveoli e olio", "Italia", "teglia", 6,
            listOf("Impasto morbido", "Riposo lungo", "Pressa gentile"),
            listOf(
                "Alta in teglia: serve tempo. L’olio protegge e aiuta a non seccare.",
                "Pressa con dita bagnate/unte: non schiacciare tutta l’aria.",
                "Sale e topping: meglio pochi, messi con criterio.",
            ))
        p(71, "Pane di farro (misto)", "Sapore e fragilità", "Europa", "farine", 4,
            listOf("Glutine diverso", "Idratazione attenta", "Fermentazione breve"),
            listOf(
                "Il farro può essere più ‘fragile’: impasto troppo lungo lo sfianca.",
                "Usalo in miscela per stabilità, soprattutto se sei all’inizio.",
                "Cerca morbidezza e profumo, non una mollica perfetta.",
            ))
        p(72, "Avena (in miscela)", "Dolcezza e umidità", "Nord Europa", "farine", 4,
            listOf("Fiocchi assorbono", "Ammollo", "Taglio facile"),
            listOf(
                "Avena: buona per morbidezza, ma va gestita con acqua (ammollo aiuta).",
                "In miscela: evita impasti troppo appiccicosi e instabili.",
                "Aspetta raffreddamento: la fetta tiene meglio.",
            ))
        p(73, "Lievito madre solida vs liquida", "Stesso mondo, due routine", "Ovunque", "fermentazione", 6,
            listOf("Velocità", "Acidità", "Stabilità"),
            listOf(
                "Solida: spesso più gestibile e meno ‘impazzita’. Liquida: più rapida ma più sensibile.",
                "Non esiste ‘migliore’: esiste quella che riesci a mantenere con costanza.",
                "Scrivi i tuoi tempi: il tuo ambiente è parte della cultura madre.",
            ))
        p(74, "Rinfresco: 1:1:1 vs 1:2:2", "Controllare i tempi", "Ovunque", "fermentazione", 4,
            listOf("Rapporto = velocità", "Segnali di picco", "Routine"),
            listOf(
                "Rapporto più alto (più farina) = fermentazione più lenta e spesso più stabile.",
                "Non inseguire ore fisse: cerca picco, profumo e consistenza.",
                "Cambia un parametro alla volta: rapporto o temperatura, non entrambi.",
            ))
        p(75, "Pasta di riporto", "Old dough, gusto senza complicazioni", "Europa", "fermentazione", 4,
            listOf("Impasto di ieri", "Aroma", "Gestione semplice"),
            listOf(
                "Pasta di riporto: tieni un pezzo di impasto e riusalo nel successivo.",
                "Dà continuità e profumo senza gestire una madre complessa.",
                "Annota quantità e tempi: è un prefermento ‘nascosto’.",
            ))
        p(76, "Scald (pregelatinizzato)", "Acqua legata, mollica più morbida", "Nord Europa", "tecnica", 5,
            listOf("Amidi cotti", "Sofficità", "Shelf-life"),
            listOf(
                "Scald: cuoci una parte di farina/cereali con acqua. L’amido lega acqua e cambia texture.",
                "Non è ‘trucco’: è un modo diverso di gestire idratazione e morbidezza.",
                "Usalo con misura: troppa parte gelatinizzata rende impasto difficile.",
            ))
        p(77, "Malto diastatico", "Spinta e colore (con prudenza)", "Ovunque", "ingredienti", 4,
            listOf("Enzimi", "Doratura", "Eccesso = gommoso"),
            listOf(
                "Malto diastatico porta enzimi: aiuta doratura e fermentazione, ma va dosato con attenzione.",
                "Se esageri: mollica può diventare appiccicosa o troppo scura.",
                "Usalo come micro-ottimizzazione, non come soluzione ai problemi base.",
            ))
        p(78, "Temperatura interna di cottura", "Quando è davvero pronto", "Ovunque", "analisi", 3,
            listOf("Termometro", "Stabilità mollica", "Raffreddamento"),
            listOf(
                "Se hai un termometro, la temperatura interna ti dà un segnale chiaro di cottura.",
                "Ma non basta: raffreddamento su griglia completa il lavoro.",
                "Regola: se tagli caldo, la mollica sembra ‘cruda’ anche se non lo è.",
            ))
    }

    val glossario: List<GlossaryEntry> = listOf(
        GlossaryEntry("Appretto", "Ultima lievitazione dopo la formatura."),
        GlossaryEntry("Autolisi", "Riposo farina+acqua prima di sale e lievito."),
        GlossaryEntry("Biga", "Pre-impasto asciutto, tipico italiano."),
        GlossaryEntry("Poolish", "Pre-impasto liquido, idratazione 100%."),
        GlossaryEntry("Pieghe", "Tecnica per rinforzare l’impasto senza impastare troppo."),
        GlossaryEntry("Laminazione", "Stesura sottile e ripieghe per struttura e aria."),
        GlossaryEntry("Pirlatura", "Rotazione per creare tensione in superficie."),
        GlossaryEntry("Idratazione", "Rapporto acqua/farina nell’impasto."),
        GlossaryEntry("Pasta madre", "Coltura di lieviti e batteri, fermentazione naturale."),
        GlossaryEntry("Puntata", "Prima fermentazione in massa."),
        GlossaryEntry("Orecchio", "Sollevamento della crosta vicino al taglio."),
        GlossaryEntry("Vapore", "Umidità in forno per espansione e crosta fine."),
        GlossaryEntry("Cocotte", "Pentola in ghisa usata come microforno."),
        GlossaryEntry("Forza (W)", "Indice di tenuta della farina alle fermentazioni."),
        GlossaryEntry("P/L", "Equilibrio tenacità/estensibilità della farina."),
        GlossaryEntry("Gelatinizzazione", "Amidi che assorbono acqua e addensano con calore."),
        GlossaryEntry("Tangzhong", "Gel di farina usato per maggiore morbidezza."),
        GlossaryEntry("Sottovitato", "Fermentazione insufficiente: mollica densa."),
        GlossaryEntry("Sovralievitato", "Fermentazione eccessiva: collasso e acidità."),
        GlossaryEntry("Maglia glutinica", "Rete proteica che trattiene i gas."),
        GlossaryEntry("Preforma", "Prima formatura leggera: organizza l’impasto prima della forma finale."),
        GlossaryEntry("Incordatura", "Impasto che ‘tiene’: la maglia è sviluppata e non si strappa facilmente."),
        GlossaryEntry("Alveolatura", "Distribuzione e dimensione dei buchi in mollica: qualità ≠ solo buchi grandi."),
        GlossaryEntry("Temperatura impasto", "Temperatura della massa: influenza velocità di fermentazione e forza percepita."),
        GlossaryEntry("Scald", "Parte di farina/cereali cotta con acqua (pregelatinizzata) per più morbidezza."),
        GlossaryEntry("Malto diastatico", "Farina/malto con enzimi che aiutano doratura e attività fermentativa (da dosare)."),
        GlossaryEntry("Pasta di riporto", "Porzione di impasto precedente riutilizzata: prefermento semplice e continuo."),
        GlossaryEntry("Fermentazione fredda", "Maturazione in frigo: più controllo e aroma, ma rischio sovra se esageri."),
        GlossaryEntry("Punto di pasta", "Momento in cui l’impasto è pronto per step successivo (segni > minuti)."),
        GlossaryEntry("Temperatura interna", "Misura che indica cottura della mollica. Utile insieme a colore e suono."),
    )

    val collezioni: List<Collection> = listOf(
        Collection(
            id = "c_europa",
            titolo = "Europa: crosta e briciole",
            descrizione = "Stili classici e farine.",
            plateIds = listOf("p001", "p002", "p003", "p005", "p006", "p007", "p008", "p009", "p047", "p048"),
        ),
        Collection(
            id = "c_tecniche",
            titolo = "Tecniche: mani e tempo",
            descrizione = "Pieghe, vapore, formatura, freddo.",
            plateIds = listOf("p004", "p028", "p029", "p030", "p031", "p032", "p033", "p034", "p035", "p036", "p037"),
        ),
        Collection(
            id = "c_mondo",
            titolo = "Mondo: pani di viaggio",
            descrizione = "Piastra, vapore, tasche.",
            plateIds = listOf("p011", "p012", "p013", "p014", "p015", "p020", "p021", "p023", "p024"),
        ),
        Collection(
            id = "c_arricchiti",
            titolo = "Arricchiti: morbidi e lunghi",
            descrizione = "Uova, burro, amidi.",
            plateIds = listOf("p018", "p019", "p026", "p027", "p039"),
        ),
        Collection(
            id = "c_base",
            titolo = "Fondamentali",
            descrizione = "Sale, idratazione, farina, lettura mollica.",
            plateIds = listOf("p040", "p041", "p042", "p043", "p050", "p051", "p052"),
        ),
        Collection(
            id = "c_scaffale",
            titolo = "Scaffale pronto",
            descrizione = "Basi pratiche da tenere in rotazione.",
            plateIds = listOf("p007", "p023", "p024", "p026", "p038", "p045"),
        ),
        Collection(
            id = "c_italia",
            titolo = "Italia: forme e teglie",
            descrizione = "Focaccia, schiacciata, piadina, grissini: tecniche e identità regionali.",
            plateIds = listOf("p006", "p007", "p025", "p056", "p057", "p058", "p070"),
        ),
        Collection(
            id = "c_fermenti",
            titolo = "Fermenti e planning",
            descrizione = "Pre-impasti, freddo e routine: gusto con controllo.",
            plateIds = listOf("p002", "p003", "p036", "p040", "p041", "p068", "p073", "p074", "p075"),
        ),
        Collection(
            id = "c_forno",
            titolo = "Forno: calore e lettura",
            descrizione = "Vapore, teglia, temperatura interna: capire la cottura.",
            plateIds = listOf("p034", "p035", "p037", "p067", "p050", "p078"),
        ),
    )

    // Micro‑tips offline (per notifiche e schermate).
    val tips: List<String> = listOf(
        "Pesa il vaso/impasto: più leggero = meno acqua residua.",
        "Cerca tensione, non forza: la maglia cresce con riposi e pieghe.",
        "Sale dopo autolisi: impasto più docile e meno ossidato.",
        "Vapore all’inizio, asciugatura alla fine: crosta migliore.",
        "Non inseguire solo alveoli: profumo ed elasticità contano di più.",
        "Fermentazione fredda = programma e gusto: ma non esagerare.",
        "Se la mollica è densa: spesso è sottovitato, non ‘farina sbagliata’.",
        "Tagli decisi: lame lente strappano e chiudono.",
        "Impasti idratati: mani bagnate, pieghe coil, zero panico.",
        "Raffredda su griglia: la mollica finisce di stabilizzarsi.",
        "Semi ammollati non rubano acqua: texture migliore.",
        "Conserva bene: tessuto per crosta, chiuso breve per morbidezza.",
        "Segna temperatura impasto: cambia più dei grammi.",
        "Se resiste alla stesura: riposa 5 minuti, non forzare.",
        "In teglia: pre-riscaldo e asciugatura finale fanno miracoli.",
        "Prefermento al picco: oltre diventa acido e perde spinta.",
        "Taglia solo da freddo: fetta migliore, mollica più stabile.",
        "Malto diastatico: micro-dosi, non scorciatoie.",
    )
}
