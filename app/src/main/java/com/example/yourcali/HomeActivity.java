package com.example.yourcali;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    ImageButton workoutButton, exercisesEncyclopediaButton, workoutPlansHistoryButton;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        Serializable object = intent.getSerializableExtra("data");
        if (object != null) {
            data = (Data) object;
        }
        else
        {
            // Data initializing
            prepareData();
        }

        // Variables link
        workoutButton = findViewById(R.id.workoutButton);
        exercisesEncyclopediaButton = findViewById(R.id.exercisesEncyclopediaButton);
        workoutPlansHistoryButton = findViewById(R.id.workoutHistoryButton);

        // Set click listener
        workoutButton.setOnClickListener(v -> {
            Intent openWorkoutPlanSelection = new Intent(HomeActivity.this, WorkoutPlanSelectionActivity.class);
            openWorkoutPlanSelection.putExtra("data", data);
            startActivity(openWorkoutPlanSelection);
        });

        exercisesEncyclopediaButton.setOnClickListener(v -> {
            Intent openExercisesEncyclopedia = new Intent(HomeActivity.this, ExercisesEncyclopediaActivity.class);
            openExercisesEncyclopedia.putExtra("data", data);
            startActivity(openExercisesEncyclopedia);
        });

        workoutPlansHistoryButton.setOnClickListener(v -> {
            Intent openWorkoutPlansHistory = new Intent(HomeActivity.this, WorkoutPlansHistoryActivity.class);
            openWorkoutPlansHistory.putExtra("data", data);
            startActivity(openWorkoutPlansHistory);
        });
        }


        private void prepareData () {
            data = new Data();

            // Esercise creation
            Exercise planche = new Exercise("Planche",BodyPart.Shoulders, Difficulty.Hard,"La Planche è una delle abilità calisteniche più impressionanti e richiede una grande forza dei polsi, delle braccia e delle spalle. Nella Planche si tiene il corpo parallelo al suolo con le gambe in aria. Ci si piega molto in avanti (mani sotto l'anca) per mantenere l'equilibrio. Le braccia devono essere dritte e i gomiti bloccati. La scapola deve essere protratta, quindi spingete la spalla verso il basso.");
            Exercise handstand = new Exercise("Hand Stand", BodyPart.Shoulders,Difficulty.Medium, "Appoggiare le mani sul pavimento alla larghezza delle spalle. Premete le spalle verso il basso e di lato. Impegnate il core e mantenete tutto il corpo in tensione. Tirate su una gamba e poi l'altra. Portate il corpo in linea retta, puntate le dita dei piedi, mantenete una posizione incavata del corpo e bilanciatelo in questa posizione. L'equilibrio avviene attraverso le dita, gli avambracci e le spalle. Se state cadendo in avanti, spingetevi indietro con la punta delle dita, aprite e premete le spalle verso il basso. Se state cadendo all'indietro, piegatevi un po' in avanti con le spalle e spingetele verso il basso, stringendo anche le gambe e gli addominali.");
            Exercise frog_stand = new Exercise("Frog Stand", BodyPart.Arms, Difficulty.Easy, "Per la  Frog Stand posizionate le mani a terra e spostate il peso sulle braccia mettendo le ginocchia a lato (o sopra) le spalle. Le braccia piegate sono consentite, ma l'obiettivo è tenerle dritte. La scapola deve essere protratta, quindi spingete la spalla verso il basso. Per il posizionamento delle mani potete usare quello che preferte ma non stressate troppo i polsi.");
            Exercise pull_up = new Exercise("Pull Up", BodyPart.Arms, Difficulty.Easy,"L'esercizio inizia in posizione di sospensione. Il movimento viene inizializzato con la depressione e la ritrazione della scapola. Poi ci si tira su impegnando i bicipiti e i muscoli dorsali. Tirare fino a quando il mento supera la sbarra (meglio ancora fino a quando il petto tocca la sbarra). Scendere lentamente e in modo controllato in posizione di sospensione. È importante eseguire sempre l'intera gamma di movimenti." );
            Exercise push_up = new Exercise("Push Up", BodyPart.Arms, Difficulty.Easy, "Iniziate in posizione plank con le braccia un po' più larghe delle spalle. Le mani devono essere in linea con le spalle. Premete le spalle verso il basso (protratte) e indietro (depresse). Ruotate il bacino verso l'interno e mantenete una buona tensione in tutto il corpo. Abbassatevi lentamente e non lasciate che i gomiti ruotino ampiamente verso l'esterno. Prima che il petto tocchi quasi il suolo, sentite come le scapole si stringono tra loro e spingetevi verso l'alto.");
            Exercise pistol_squat = new Exercise("Pistol Squat", BodyPart.Legs, Difficulty.Hard,"Il Pistol Squat si esegue stando in piedi su una gamba sola. Scendere lentamente e in equilibrio, mantenendo il peso sul tallone e allungando l'altra gamba in aria. La posizione di fondo si raggiunge quando il sedere tocca quasi il tallone. Fare una breve pausa e poi accovacciarsi, facendo attenzione a girare il ginocchio verso l'esterno e a spingere dal tallone. Durante l'intero movimento mantenete la tensione negli addominali per migliorare l'equilibrio. Un'esecuzione corretta e mirata del Pistol Squat è molto importante. Se si avverte dolore alle ginocchia, è necessario interrompere l'esecuzione . È necessario allenare sempre entrambi i lati.");
            Exercise plank = new Exercise("Plank", BodyPart.Abs, Difficulty.Easy, "Piegate le braccia a 90° e stendete gli avambracci a terra. Le gambe devono essere dritte e unite. Assumere una posizione orizzontale e diritta del corpo. Il bacino deve essere normale o inclinato all'indietro. Questo è molto importante, perché si può barare facilmente inarcando la schiena o portando i glutei in alto. Inoltre, le scapole devono essere depresse e protese. Mantenete questa posizione.");
            Exercise hollow_body_hold = new Exercise("Hollow Body Hold", BodyPart.Abs, Difficulty.Easy, "Sdraiarsi con la schiena a terra. Raddrizzate le gambe e le braccia. . Stringete le gambe, impegnate il core e ruotate le anche all'indietro (inclinazione pelvica posteriore). Cercate di ridurre al minimo o di eliminare l'insieme tra la schiena e il pavimento. Diventa più facile quanto più si alzano le braccia e le gambe. Cercate di tenere le braccia e le gambe il più in basso possibile, senza creare un tutt'uno tra il pavimento e la schiena.");
            Exercise ice_cream_cakers = new Exercise("//Ice Cream Makers", BodyPart.Back, Difficulty.Medium, "Iniziare in posizione di sospensione e fare un Pull Up. Mantenetevi nella posizione più alta, quando il mento raggiunge la sbarra. Inclinarsi all'indietro e portare i fianchi verso l'alto spingendo le braccia dritte. Mantenere la posizione di Front Lever solo per un secondo e tornare alla posizione di Pull Up Hold. Ricordate di ritrarre la scapola e di mantenere il corpo dritto.");
            Exercise one_arm_hand_stand = new Exercise("One Arm Hand Stand", BodyPart.Shoulders, Difficulty.Hard, "Passare alla verticale. Mettetevi a cavalcioni sulle gambe. Spostate il peso su un lato. Con il controllo, allontanare una mano dal pavimento. Mantenere la forma corretta della verticale. Ciò significa braccia dritte e spalle sollevate. Posizione pelvica posteriore. Spingere con forza con il braccio e la spalla attivi, per evitare che il corpo ruoti. Usare le gambe a cavalcioni per aiutarsi nell'equilibrio.");
            Exercise dip = new Exercise("Dip", BodyPart.Chest, Difficulty.Easy, "Questo esercizio si esegue alle parallele. Iniziare in posizione di appoggio verticale. Se le sbarre non sono abbastanza alte, potete piegare le gambe. Mantenete il corpo dritto e i gomiti in dentro, in modo da colpire i tricipiti. Se ci si piega in avanti e si lasciano i gomiti in fuori, la tensione si concentra maggiormente sul petto e sulle spalle. Abbassatevi fino a formare un angolo di almeno 90° con le braccia. Spingetevi verso l'alto fino alla posizione iniziale senza oscillare.");
            Exercise lunge = new Exercise("Lunge", BodyPart.Legs, Difficulty.Easy, "Iniziare in posizione eretta. Fare un grande passo in avanti e appoggiare i piedi a terra con il tallone per primo. Portate il corpo in avanti e verso il basso, portando il peso maggiore sulla gamba d'appoggio. Questa gamba deve essere piegata a 90°. Non lasciare che il ginocchio superi le dita dei piedi e che il ginocchio posteriore tocchi il suolo! Inoltre, mantenete sempre la parte superiore del corpo dritta. Con la gamba di punta spingetevi indietro e unite i piedi per tornare alla posizione di partenza.");
            Exercise human_flag = new Exercise("Human Flag", BodyPart.Abs, Difficulty.Medium, "Il braccio inferiore preme sul punto di ancoraggio con le scapole sollevate. Allo stesso tempo, il braccio superiore aiuta a bilanciare il corpo teso, tirando con i dorsali e deprimendo la scapola. Mantenete entrambe le braccia dritte. Impegnate gli obliqui per ruotare lateralmente le gambe dritte. Il punto finale è raggiunto quando le gambe sono parallele al suolo. Mantenete il corpo dritto e aprite il petto, che deve essere perpendicolare al pavimento. I muscoli più importanti in questa attività sono le spalle, perché lì si esercita la massima forza di gravità. Per evitare squilibri è necessario allenare sempre entrambi i lati.");
            Exercise jumping_jacks = new Exercise("Jupming Jacks", BodyPart.Legs, Difficulty.Easy, "Fase 1: posizione di partenza. Unite le gambe e i piedi. Mettete le braccia dritte di lato, con le mani che toccano le cosce.\n" + "\n" + "Fase 2: saltare leggermente e divaricare le gambe oltre la larghezza delle spalle. Allo stesso tempo, portare le braccia in alto lateralmente, finché le mani non si toccano sopra la testa.\n" + "\n" + "Fase 3: cercare di atterrare dolcemente sulla pianta del piede, compensando l'impatto. Mantenete inoltre la tensione del corpo per tutto il tempo e i glutei impegnati. Evitare che le ginocchia si girino troppo verso l'interno/esterno. "+"\n"+"Fase 4: saltate di nuovo nella posizione di partenza e cercate di nuovo di atterrare dolcemente.\n");
            Exercise archer_push_up = new Exercise("Archer Push Up", BodyPart.Arms, Difficulty.Medium, "Iniziare in posizione plank con le braccia divaricate. Premete le spalle verso il basso (protratte) e indietro (depresse). Ruotate i bacini in dentro e mantenete una buona tensione in tutto il corpo. Abbassatevi lentamente su un lato piegando un braccio e mantenendo l'altro dritto (più piegate il braccio di assistenza, più l'esercizio diventa facile). Non lasciate che il gomito del braccio piegato si rivolga ampiamente verso l'esterno. Prima che il petto tocchi quasi il suolo, sentite come le scapole si stringono tra loro e spingetevi verso l'alto.");
            Exercise muscle_up = new Exercise("Muscle Up", BodyPart.Arms, Difficulty.Medium, "Il Muscle-Up può essere suddiviso in 3 fasi:\n" + "\n" + "1. Trazioni\n" + "2. Transizione\n" + "3. Spinta\n" + "\n" + "Per prima cosa si tira il corpo il più in alto possibile (petto o anche anca a livello della sbarra). La parte probabilmente più difficile è la transizione. Per riuscirci, è necessario ruotare i polsi sopra la sbarra e lanciare le spalle oltre la sbarra. L'ultimo passo è il Dip alla sbarra.\n" + "\n" + "Il Muscle-up richiede quasi tutti i muscoli della parte superiore del corpo. Richiede esplosività e una buona coordinazione dei muscoli. Il Muscle-up può essere eseguito anche con gli anelli da ginnastica. Sugli anelli si vede spesso una falsa presa (partenza in sospensione con i polsi sopra gli anelli).\n");
            Exercise front_lever = new Exercise("Front Lever", BodyPart.Back, Difficulty.Hard, "Il Front Lever è un esercizio di Calisthenics avanzato che consiste nel tenere la parte posteriore del corpo parallela al suolo, sostenuta solo dalle braccia. Le braccia devono essere dritte e il corpo ben saldo. Ritraete la scapola (spingete le spalle indietro) e mantenete i fianchi alti (al livello delle spalle).");
            Exercise bicycle = new Exercise("Bicycle", BodyPart.Abs, Difficulty.Easy, "Fase 1: iniziare stendendo la schiena, portare le mani alla testa, sollevare la testa e le spalle dal pavimento e stendere le gambe dritte. Coinvolgere il core e i glutei, avere la schiena piatta contro il pavimento, non ci deve essere arco e spazio tra la parte bassa della schiena e il pavimento.\n" + "\n" + "Fase 2: portare una gamba in dentro, piegandola a circa 90°. L'altra gamba rimane dritta. Più la tenete bassa, più è difficile per il core inferiore. Più la gamba passiva è alta, più è facile. Nello stesso momento in cui portate la gamba in dentro, ruotate il busto, fate un crunch e portate il gomito opposto alla gamba piegata. NON portate il mento in dentro, ma mantenete una posizione neutra della testa.\n" + "\n" + "Fase 3: allungare di nuovo la gamba, portare l'altra in dentro e ruotare dall'altra parte. Toccare nuovamente il ginocchio con il gomito opposto. Il tocco su entrambi i lati conta come una ripetizione\n");
            Exercise squat = new Exercise("Squat", BodyPart.Legs, Difficulty.Easy,"Lo squat si esegue con i piedi alla larghezza delle spalle. Contrarre gli addominali, tenere la schiena dritta e guardare in avanti e non in basso (questo vi aiuterà a mantenere la schiena dritta). Abbassarsi con il peso sui talloni. Non lasciate che le ginocchia si girino verso l'interno. Abbassatevi il più possibile mantenendo una buona forma fisica. L'obiettivo è avere l'anca a livello delle ginocchia. Al termine, spingersi verso l'alto con i talloni, mantenendo una buona forma fisica.");

            // Exercise adding
            data.addPredefinedExercise(planche);
            data.addPredefinedExercise(handstand);
            data.addPredefinedExercise(frog_stand);
            data.addPredefinedExercise(pull_up);
            data.addPredefinedExercise(push_up);
            data.addPredefinedExercise(pistol_squat);
            data.addPredefinedExercise(plank);
            data.addPredefinedExercise(hollow_body_hold);
            data.addPredefinedExercise(ice_cream_cakers);
            data.addPredefinedExercise(one_arm_hand_stand);
            data.addPredefinedExercise(dip);
            data.addPredefinedExercise(lunge);
            data.addPredefinedExercise(human_flag);
            data.addPredefinedExercise(jumping_jacks);
            data.addPredefinedExercise(archer_push_up);
            data.addPredefinedExercise(muscle_up);
            data.addPredefinedExercise(front_lever);
            data.addPredefinedExercise(bicycle);
            data.addPredefinedExercise(squat);






            // Exercise lists creation
            ArrayList<ExerciseOfPlan> exerciseList = new ArrayList<>();

            // Exercise of plan creation
            ExerciseOfPlan plancheOfPlan = new ExerciseOfPlan(planche, 3, 30, 2, ExerciseUnity.Seconds, RestUnity.Minutes);

            //Exercise of plan adding
            exerciseList.add(plancheOfPlan);

            // Workout plan creation
            PredefinedWorkoutPlan predefinedWorkoutPlan1 = new PredefinedWorkoutPlan("Scheda", "Descrizione", exerciseList, Difficulty.Medium);

            // Workout plan adding
            data.addPredefinedWorkoutPlan(predefinedWorkoutPlan1);
        }

}