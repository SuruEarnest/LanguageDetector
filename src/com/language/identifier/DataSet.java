/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.language.identifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;


/**
 * @author      Suru Earnest Erihbra <serihbrah@gmail.com>
 * @version     1.0                
 * @since       2015-12-02         
 */


public class DataSet {

    private ArrayList<String> langClass = new ArrayList<>();//this is a list of all the languages
    private final Set<String> vocabulary = new HashSet<>(); //this is a set of all the tokens generated from the training documents
    //this is helps to map each language category to its training documents
    private HashMap<String, ArrayList<String>> trainingCorpus = new HashMap<>();
    //this is a list of all the list of documents
    private List<List<String>> allDocsList = new ArrayList<>();
    //this shall be used the number of training corpus
    private int numberOfTrainingDocs;

    
    public DataSet() {

    }

    
    /**
     * this helps to load all the hard coded training documents in string format
    */
    public void loadTrainingData() {

        String docEng1 = "On 30 September 2015, Russia started a military intervention in the Syrian Civil War.[59] It consisted of air strikes"
                + " by Russia against"
                + " militant groups opposed to Syrian government, "
                + "primarily al-Nusra Front and Jaysh al-Fatah (the Army of Conquest).[60][61][62] Russian army had significant ground support"
                + " from Iranian ground"
                + " troops, the Syrian military, and allied militias."
                + "[63] Prior to the intervention, Russian involvement in the Syrian Civil War had mainly consisted of arming the Syrian Army.[64] "
                + "Russia intervened"
                + " after a formal request from the Syrian government "
                + "for military help against rebel and jihadist groups in Syria.[65] Most Russia's airstrikes during the first week of the campaign "
                + "struck areas held"
                + " by rebel groups opposed to both the Syrian government"
                + " and the Islamic State of Iraq and the Levant.The Russian defense ministry announced on 9 October that up to sixty ISIL targets were hit in the past 24 hours, supposedly killing 300 militants in the most intense strikes so far. One of the raids targeted a Liwa al-Haqq base in the Al-Raqqah Governorate using KAB-500KR precision-guided bombs, in which two senior ISIL commanders and up to 200 militants were killed, despite the lack of connection between Liwa al-Haqq and ISIL. Another assault destroyed a former prison near Aleppo that was used by ISIL as a base and munitions depot, also killing scores of militants. Rebel training sites in the Latakia and Idlib provinces were allegedly hit as well.[101] Meanwhile, ISIL militants made advances in the Aleppo area on 9 October, seizing several villages, including Tal Qrah, Tal Sousin, and Kfar Qares, in what the Associated Press called a \"lightning attack\". The attacks were unencumbered by either Russian or U.S.-led coalition airstrikes. The ISIL advance came at the expense of rebel groups also targeted by Russian and Syrian forces.[102]\n"
                + "The Quds Force under General Qasem Soleimani has been involved in support of the Assad government since the very beginning of the Syrian Civil War in 2011. This support has taken many forms, chief among them military support in terms of training, logistics, deployments of allied militias to Syria (particularly Shi'ite militias), operational and strategic planning, intelligence-sharing and weaponry. Iran is estimated to be providing the equivalent of many billions of dollars in financial aid.[41] Key victories were achieved with substantial support provided by the Quds force, namely the al-Ghab plains battles, Aleppo offensives, Dara'aya offensives of 2015 and the al-Qusayr offensives which established government and Hezbollah control over the northern Qalamoun region and the border crossings from Lebanon to Syria.\n"
                + "\n"
                + "After the loss of Idlib province to a rebel offensive in the first half of 2015, the situation was judged to have become critical for Assad's survival. High level talks were initiated between Moscow and Iran which continue to this day which involved the Russian and Iranian foreign ministers, concerning possible solutions to the Syrian conflicts. On 24 July, ten days after the signing of the nuclear agreement between Iran and the P5+1 countries, General Qasem Soleimani visited Moscow.[103] Though the exact content of the meeting between Soleimani and Putin is widely speculated about, there is growing consensus that the chief matter at hand was a plan of coordinated escalation of military forces in Syria.[104]\n"
                + "\n"
                + "In mid-September 2015, the first reports of new detachments from the Iranian revolutionary guards arriving in Tartous and Latakia in west Syria were made. With much of the SAA and NDF units deployed to more volatile fronts, the Russian Marines and Iranian Revolutionary Guard (IRG) have relieved their positions by installing military checkpoints inside the cities of Slunfeh (east Latakia Governorate), Masyaf (east Tartous Governorate) and Ras Al-Bassit (Latakia coastal city).[105] There were also further reports of new Iranian contingents being deployed to Syria in early October 2015.[106] It is generally thought that Iran will be playing a leading role in the ground operations of Syria's army and allies, whilst Russia will be leading in the air in conjunction with the Syrian air force, thereby establishing a complementary role.[15]\n"
                + "\n"
                + "Outside of the battlefield itself the Iranian government has dramatically increased its efforts in supporting the Syrian government and its military. Recently there has been an exponential increase in recruitment efforts aimed at drawing on Shi'ite militias in Iraq in order to deploy them to Syria.[23] One of the leading militias in Iran's efforts to bolster the war effort in Syria is Kata'ib al-Imam Ali, with Jaafar al-Bindawi, the militia's former head of training and logistics, would be leading the deployment in Syria, while Ali Nizam would serve as the new logistical director for Syrian affairs. Another important allied militia in this context is Harakat Hezbollah al-Nujaba which similar to Kata'ib al-Imam Ali was formed with Iranian assistance from mainly former Sadrist supporters and fighters. There is increasing evidence that these Iranian-backed militias are not only operating under Iranian guidance but are also cooperating with the Russian military command established in Syria.[23]\n"
                + "\n"
                + "On 8 October reports came in of the death of General Hamadani, the deputy to General Qasem Soleimani in Syria. Initially it was claimed that he fell to gun-fire from ISIL militants in northern Aleppo whilst advising the troop build-up on that front.[107] However it later transpired that the cause of his death was an auto-mobile accident.[2] It is thought that his death will significantly delay the ground-operations in northern Syria as Hamadani was the primary strategist in the planning of the coming operations.[2]\n"
                + "Syria: The Syrian ambassador to Moscow, Riyad Haddad stated that the Russian air force is acting in full coordination with the Syrian army. He added that Syria's position is that the Russian intervention is the only legitimate intervention under international law, and called for other countries to join what he termed the \"non-criminal\" Russian-led intervention in Syria.[75]\n"
                + "\nTurkey's president Recep Tayyip Erdogan, after a series of alleged violations of the country's airspace by Russian military aircraft in early October 2015, warned that Russia’s military operation in Syria could jeopardise the bilateral ties between the countries.[117]\n"
                + "The Russian Federation: On 27 September 2015, President Vladimir Putin told journalist Charlie Rose that \"More than 2,000 fighters from Russia and ex-Soviet republics are in the territory of Syria. There is a threat of their return to us. So, instead of waiting for their return, we are better-off fighting them on Syrian territory\".[118] On 30 September 2015, the Russian Orthodox Church spokesman Vsevolod Chaplin, talking about the support of the government's action on the part of all the religious groups in Russia, said the fight against terrorism was a \"moral fight, a holy fight if you will\".[119][120] Leader of the Central Spiritual Administration of Muslims of Russia, Chief Mufti Talgat Tadzhuddin stated: \"We fully back the use of a contingent of Russian armed forces in the battle against international terrorism.\"[121]\n"
                + "\n"
                + "On 1 October 2015, President Vladimir Putin dismissed reports of alleged casualties among civilians caused by Russian airstrikes in Syria as \"information warfare\" against Russia, adding that these claims had begun before the planes used in the airstrikes had even taken off.[122] The Russian Defence Ministry made a claim that satellite images confirm that some air strikes hit alleged ISIS positions.[123]\n"
                + "\n"
                + "Iran: Iran was confirmed as having hundreds of troops in Syria ready for combat against ISIS.[124]\n"
                + "\n"
                + "Iraq: In early October 2015, the top officials of Iraq indicated that they would welcome Russian air strikes on Islamic State militants in Iraq and wanted Russia to have a bigger role than the U.S. in the war against the militant group.[125]\n"
                + "Egypt: On 3 October 2015, Egypt's foreign minister Sameh Shoukry said the Russian entry into war in Syria was bound \"to have an effect on limiting terrorism in Syria and eradicating it\".[126]\n"
                + "\n"
                + "China: China denied sending warships and troops to Syria after rumors about it were circulated around the internet.[127]\n"
                + "\n"
                + "Israel: Israel was given advance warning of the airstrikes and has set up a working group to \"coordinate on everything\" with the Russians.[128] They were particularly concerned about ensuring the de facto alliance between Hezbollah and Russia won't lead to any unfortunate incidents.[129] According to Zvi Magen, former Israeli ambassador to Moscow, “Israel made clear to him [Putin] that we have no real problem with Assad, just with Iran and Hizbullah, and that message was understood.”[130]";
        String docEng2 = "The Boko Haram insurgency began in 2009,[25] when the jihadist rebel group Boko Haram started an armed rebellion against "
                + "the government of Nigeria."
                + "[26] In 2013, more than 1,000 died in the conflict. The violence escalated dramatically in 2014, with 10,849 deaths.The insurgency "
                + "has since spread to Cameroon, "
                + "Chad, and Niger thus becoming a major regional conflict.\n"
                + "\n"
                + "The insurgency takes place within the context of long-standing issues of religious violence between Nigeria's Muslim and Christian "
                + "communities.\n"
                + "Nigeria was amalgamated both the Northern and Southern protectoriate in 1914, only about a decade after the defeat of the Sokoto "
                + "Caliphate and other "
                + "Religious conflict in Nigeria goes as far back as 1953. The Igbo massacre of 1966 in the North that followed the counter-coup "
                + "of the same year had as a dual cause the Igbo officers' "
                + "coup and pre-existing (sectarian) tensions between the Igbos and the local Muslims. This was a major factor in the Biafran "
                + "secession and the resulting civil war.";
        ArrayList<String> englishDocs = new ArrayList<>();
        englishDocs.add(docEng1);
        englishDocs.add(docEng2);

        ArrayList<String> igboDocs = new ArrayList<>();

        String igboDocs1 = "Ajọ Ifufe Joaquin (Spanish pronunciation: [xwa'kin] wah-nkọ) [1] bụ ifịk extratropical naa n'elu ugwu Atlantic Ocean ugbu a egwu Iberian Peninsula. Ọ idiọk impacted nnukwu akụkụ nke Bahamas na nso nso emetụta Bermuda. Ọ bụ otu ụzọ n'ụzọ iri aha ya bụ oké mmiri ozuzo, atọ hurricane na nke abụọ isi hurricane nke 2015 Atlantic hurricane oge. Si a na-abụghị ebe okpomọkụ ala, Joaquin mbụ nkewa a okpomọkụ ịda mbà n'obi na September 28, ọma n'ebe ndịda ọdịda anyanwụ nke Bermuda. Na ike ifufe shiee akwụgo ịda mbà n'obi ibido otụhọde ike mgbe na-akpụ akpụ ji nwayọọ nwayọọ na ndịda ọdịda anyanwụ. Mgbe ịghọ a okpomọkụ oké ifufe na-esote ụbọchị, Joaquin wara a frasi nke ngwangwa intensification n'elu-ekpo ọkụ oké osimiri okpomọkụ na abating shiee, na-eru hurricane ọnọdụ na September 30 na isi hurricane ike on October 1. Meandering n'elu ndịda Bahamas, Joaquin anya gafere nso ma ọ bụ n'elu ọtụtụ agwaetiti n'elu na-esonụ ụbọchị abụọ. On October 3 na hurricane ebelatawo dịtụ na accelerated ugwu ọwụwa anyanwụ. "
                + "Mberede re-intensification malitere mgbe e mesịrị n'ụbọchị ahụ, na Joaquin enwetara nyere ifufe nke 155 mph (250 km / h), emejupụta "
                + "ya elu ike. Nke a mere ka ọ ike Atlantic hurricane ebe Ajọ Ifufe Igor ke 2010.\n"
                + "\n"
                + "Ajọ Ifufe ịdọ aka ná ntị a zụlitere gafee ukwuu nke Bahamas on September 30 dị ka hurricane egwu mba. Etipú mba n'ebe ndịda agwaetiti ihe karịrị ụbọchị abụọ, Joaquin mere ọtụtụ mbibi; Acklins, gbagọrọ agbagọ Island, Long Island, Rum Cay na San Salvador Island kwagidere ndị kasị njọ mebiri. Obodo ndị fọdụrụ dịpụrụ adịpụ na idei mmiri ruo ọtụtụ ụbọchị. Offshore na n'ụgbọ mmiri El Faro wee na-efu na 33 oru ugbo; mgbe ọtụtụ enyocha United States Coast Nche-ekwere na arịa yiri kpuo. E mesịa, ndị ike gwụ hurricane gafere nnọọ n'ebe ọdịda anyanwụ Bermuda, buffeting agwaetiti na ike ifufe mere ka ike he na obere mebiri.\n";
       
        String igboDocs2 = "Russia malitere a agha aka na Siria Civil War. [59] Ọ ẹkewetde nke ikuku etiwapụ site Russia megide agha dị iche iche na-emegide Siria ọchịchị, isi al-gakwuuru Front na Jaysh al-Fatah (ndị agha imeri) . [60] [61] [62] Russian agha nwere ịrịba n'ala nkwado si Iranian n'ala agha, Siria agha, na-ejikọ militias. [63] Tupu aka, Russian itinye aka na Siria Civil Agha tumadi ẹkewetde nke jikeere na Siria Army. [64] Russia na-etinye aka mgbe a iwu arịrịọ si Siria ọchịchị agha enyemaka megide nnupụisi na jihadist iche iche na Syria.Kasị Russia si airstrikes n'oge izu mbụ nke mkpọsa gburu ebe ẹkenịmde nnupụisi iche iche na-emegide ma ndị Siria ọchịchị na islam State nke Iraq na Levant\n" +
"\n" +
"The United States, nke na-emegide ma islam State na Siria ọchịchị, ka chịrị si agha imekọ ihe ọnụ na Russia na Syria. [72] Secretary nke Defense Ashton Carter na ndị ọzọ na agadi US ọchịchị kwuru Russia si mkpọsa bụ isi iji na propping elu Jn, onye President Barack Obama ugboro ugboro na-akpọ n'elu na-ahapụ ike ya, [nke 67] ihe ntule na-akọrọ site France. [69] The US e eduga a iche iche mkpọsa na Syria kemgbe September 2014, nakwa dị ka otu onye n'ime Iraq ebe June 2014, ma iji nanị megide islam State na al Qaeda mmekọ.\n" +
"Siria Civil Agha a na-busoro n'etiti multiple mmegide na ọchịchị dị iche iche na ha na mpaghara na mba ọzọ na nkwado bases, na-ekere òkè a shifting, mgbagwoju weebụ nke ofu-mmekọrịta. Dị ka ọtụtụ ọnwa nke ikuku etiwapụ ekenịmde ke US-ada mmekota ostensibly megide ISIL zaa pụtara ìhè n'ihu akpatawo n'ihu mgbasa na ike nke ISIL,Russia, jikọrọ aka Syria, ke September 2015 malitere eziga ya Russian-ejikwara warplanes na ndị ọzọ na agha ngwaike, nakwa dị ka ọgụ agha, na-airbase nso n'ọdụ ụgbọ mmiri n'obodo Latakia na Syria. The edinam e eme na ihe ukara arịrịọ site Siria ọchịchị gawa site President Bashar al-Jn. [75]\n" +
"\n" +
"Ná ngwụsị nke September 2015, a nkwonkwo ọmụma center na Baghdad e guzobere site Iran, Iraq, Russia na Syria na-ahazi ha arụmọrụ megide islam State. [Nke 76]\n" +
"\n" +
"On 30 September 2015, na elu n'ụlọ Russian Federal Assembly, na Federation Council, nyere arịrịọ site Russian President Vladimir Putin idokwa Russian Air Force na Syria. [77] On otu ụbọchị, Russia nnọchianya ka nkwonkwo ọmụma center rutere US Embassy na Baghdad ma rịọ ka ọ bụla US agha na ezubere iche ebe ke Syria ahapụ ozugbo.Otu awa ka e mesịrị, Russian ụgbọelu dabeere na gọọmenti na-ẹkenịmde n'ókèala malitere eduzi airstrikes ostensibly megide islam State zaa.\n" +
"Mbụ usoro nke ikuku etiwapụ ama ada itie ke 30 September 2015 na ebe gburugburu obodo nke Homs na Hama, ezubere iche ndị mainstream mmegide. [59] Russian warplanes wakpoo nnupụisi ọnọdụ \"na al-Rastan, Talbiseh na Zafaraniya na Homs n'ógbè; i Tilol al-Hmer na Quneitra n'ógbè; Aydoun, bụ obodo nta nke dị ná mpụga nke obodo nke Salamiya; Deer ndedehe, n'etiti Hama na Homs; na ná mpụga Salmiya \". [80] Na ngụkọta, 20 flights e mere. [81] Ọtụtụ n'ime ndị mbụ airstrikes ezubere iche ọnọdụ nke Chechen alụso, islam ihu si Jaysh al-Islam (Army nke Islam), [82] na Free Siria Army. [83] [84] [85]\n" +
"\n" +
"Dị ka Hezbollah media tinyekwa Al Mayadeen, na Saudi / Turkish-kụziri Army nke imeri [7] gburugburu Jisr ash-Shugour e tụrụ bọmbụ na 1 October site Russian ụgbọ elu; dịkarịa ala, 30 ikuku etiwapụ e rụrụ. [14] ọzọ usoro nke Russian airstrikes rụrụ na otu ụbọchị kụrụ ISIL ọnọdụ ke Raqqa Governorate. [3]\n" +
"\n" +
"On 2 October, isi nke ndị mba ọzọ na-metụtara kọmitii na Russian nzuko omeiwu, Aleksey Pushkov, kwuru na Russian airstrikes nwere ike ịga na atọ na ọnwa anọ na ga nditetịm; [86] o mesịrị kwuru na nke a bụ a mere surmise nke ya ma ọ bụghị privy ọ bụla ihe ọmụma na nke. [87] Na ụtụtụ, Russian Air Force ulo oru anọ airstrikes on Isis na oge ochie Syriac obodo Al-Qaryatayn, na Teefor-Palmyra n'okporo ụzọ, Homs n'ógbè. [22 ] An Isis iwu na-achịkwa center e bibiri na otu airstrike na Al-Qaryatayn, mgbe ihe Isis convoy na ụzọ ha na ndị Teefor-Palmyra okporo ụzọ e wakporo. [22] Ịgbaso airstrikes, Siria Army na National Defence Forces kewapụrụ Isis si nke obodo nke Maheen kwupụta Al-Qaryatayn mgbe a abụọ hour njikọ aka na gburu 18 militants ma bibie abụọ technicals ndokwasa na zu-23-2s. [22] Siria agha mgbe ahụ ulo oru a counter-agha South-West nke Al-Qaryatayn ka naghachi isi ụzọ. [22]\n" +
"\n" +
"Na otu ụbọchị, ndị Russian Air Force malitere bọmbụ Al-gakwuuru Front [88] ọnọdụ ke al-Rastan na Talbiseh na Homs n'ógbè. [12] Mgbe e mesịrị, ha wee na bọmbụ Al-gakwuuru na Kafr Zita, Al-Ghaab n'Ọzara , Kafr Nabl, Kafr Sijnah, na Al-Rakaya na Hama n'ógbè. [12] The Siria Air Force na Russian Air Force jikotara aka wee tụrụ bọmbụ Al-gakwuuru na Jisr al-Shughur. [12] N'abalị, Russian Air Force ezubere iche Isis na 11 airstrikes n'elu Al-Raqqah mgbe ezubere iche eletriki grids n'èzí ya, abụọ airstrikes n'elu Shadadi-Hasakah okporo ụzọ, na atọ airstrikes na Al-Mayadeen, gba gburu gburu Ezek-Zor n'ógbè. [12] The isi Isis agha isi na Tabaqa Military Airport bụ nwekwara wakporo, na n'ogige ndị agha na-ebibi abụọ airstrikes. Nso Military Airport, ihe Isis ngwá agha ọkọnọ mbusa akwụkwọ na Al-'Ajrawi Ugbo na-tụrụ bọmbụ. N'otu oge ahụ, Aịsis isi n'isi ụlọ ọrụ dị Tabaqa National Hospital e kpamkpam mebiri emebi na a Russian airstrike. [12] [13] Na Al-Hasakah anāchi achi, na Russian Air Force ezubere iche Isis na Al-Shadadi na Al-Houl, mgbe Siria Air Force wakpoo ihe Isis convoy tinyere gba gburu gburu Ezek-Zor-Hasakah okporo ụzọ. [13]\n" +
"\n" +
"On 3 October, akụkọ gosiri na Hezbollah na Iranian alụso nọ na-akwadebe isi ala offensives na-achikota na Russian airstrikes. [89] Dị ka CNN, Russia agbachitere ozi kwuru, sị ya agha tụrụ bọmbụ itoolu Isis ọnọdụ nso ụjọ otu si de facto isi obodo na Raqqa . Ọ dịkarịa ala 11 e gburu ihe ahụ e boro abụọ iku site Russia na Syria si Idlib Province, dị ka mmegide dị iche iche. [Nke 90] Mgbe ụbọchị, na Russian Air Force mere anọ airstrikes n'elu Al-gakwuuru achịkwa Jisr al-Shughur, na ndị ọzọ nọ Jabal Al-Zawiya, na Jabal al-Akrad. [91] Otu n'ime lekwasịrị anya bụ ihe Al-gakwuuru mkwado convoy n'isi si Jisr al-Shughur n'ebe ugwu ọwụwa anyanwụ obodo nke Latakia n'ógbè. [91]\n" +
"\n" +
"On n'ụtụtụ nke 7 October 2015, dị ka Russian ọchịchị, anọ warships si Russian n'Òtù Ndị Agha Mmiri si Caspian Flotilla ulo oru 26 3M-14T Kalibr NK [92] nrughari akụ ụta na kụrụ 11 zaa n'ime Siria ókèala. The akụ ụta gabiga Iranian na Iraqi airspace iji ruo ha zaa nsannsan karịrị nnọọ banyere 1,500 kilomita (930 kilomita). [93] Otu ụbọchị, Siria n'ala agha e kọrọ na-ebu ihe rụrụ n'okpuru Russian ikuku cover. [94] [95] Dị ka unnamed US agha ọgụgụ isi ọchịchị, 4 nke 26 nrughari akụ ụta na 8 October jiri na Iran, ọma tupu eru ha zaa ke Syria. Russia ekwu ya nile akụ ụta kụrụ ha zaa. [96] [97] Iran nwekwara gọrọ agọ ọ bụla agha okuku na ókèala ya. [98] [99] Iranian agbachitere ozi jụrụ ọ bụla na-akọ alleging na anọ nke 26 nrughari akụ ụta jiri na Iran sị CNN akụkọ bụ akụkụ nke West si \"psychological agha\". [100]\n" +
"\n" +
"The Russian agbachitere ozi mara ọkwa na 9 October na ruo iri isii ISIL zaa e kụrụ na n'oge gara aga awa 24, e weere na-egbu 300 militants na kasị ufiop etiwapụ nnọọ. Otu n'ime raids ezubere iche a Liwa al-Haqq isi ke Al-Raqqah Governorate iji KAB-500KR nkenke-eduzi bombs, na nke abụọ agadi ISIL isi na ruo 200 militants e gburu, n'agbanyeghị enweghị njikọ dị n'etiti Liwa al-Haqq na ISIL. Ọzọ wakpo ebibi a bụbu mkpọrọ nso Aleppo e ji site ISIL dị ka isi na munitions ebusa, bụkwa igbu ọtụtụ nke militants. Rebel ọzụzụ saịtị na Latakia na Idlib anāchi achi e kwuru kụrụ dị ka mma. [101] Ka ọ dịgodị, ISIL militants mere ọganihu na Aleppo ebe on 9 October, n'iji ọtụtụ obodo, gụnyere Tal Qrah, Tal Sousin, na Kfar Qares, na ihe Associated Press na-akpọ a \"àmụmà agha\". The ọgụ ndị n'enweghị oké ibu ọrụ site ma Russian ma ọ bụ US-ada mmekota airstrikes. The ISIL tupu wee na-efu nke na-enupu isi iche iche nwekwara ezubere iche site Russian na Siria agha. [102]\n" +
"The Quds Force n'okpuru General Qasem Soleimani e abuana na ihe nkwado nke Jn ọchịchị ebe ọ bụ na mmalite nke Siria Civil Agha na 2011. Nke a nkwado emewo ọtụtụ iche-iche, isi n'etiti ha agha nkwado na okwu nke ọzụzụ, lọjistik, deployments nke na-ejikọ militias Syria (karịsịa Shi'ite militias), arụmọrụ na-emeri na atụmatụ, ọgụgụ isi-nkekọrịta na ngwá agha. Iran-eme atụmatụ na-enye Ẹkot ọtụtụ ijeri dollar na enyemaka ego. [41] Key mmeri e enweta na ọkpụrụkpụ nkwado nyere site Quds ike, ya bụ na al-Ghab ọzara agha, Aleppo offensives, Dara'aya offensives nke 2015 na al-Qusayr offensives nke guzobere ọchịchị na Hezbollah akara n'elu ugwu Qalamoun mpaghara na ókè-ala crossings si na Lebanon Syria.\n" +
"\n" +
"Mgbe ọnwụ nke Idlib ógbè na a na-enupu isi iwe na akpa ọkara nke 2015, ọnọdụ ikpe ka na-akatọ maka Jn si lanarị. Elu larịị e nwere okwu butere n'etiti Moscow na Iran nke-anọgide na-ta nke gụnyere Russian na Iranian mba ọzọ ozi, banyere omume ngwọta ndị Siria esemokwu. On 24 July, ụbọchị iri mgbe aka n'ime nuclear nkwekọrịta n'etiti Iran na P5 + 1 mba, General Qasem Soleimani gara Moscow. [103] Ọ bụ ezie kpọmkwem ọdịnaya nke nzukọ n'etiti Soleimani na Putin na-ebe speculated banyere, e na-eto eto otutu mmadu kwenyere na ndi-isi-okwu na aka bụ a atụmatụ nke achikota escalation ndị agha na Syria. [104]\n" +
"\n" +
"Ke ufọt ufọt September 2015, mbụ akụkọ nke ọhụrụ detachments si Iranian revolutionary nche rutere na Tartous na Latakia na n'ebe ọdịda anyanwụ Syria e mere. Na ukwuu nke SAA na NDF nkeji deployed ọzọ obodo asakde iso ese, na Russian Marines na Iranian Revolutinary Nche (IRG) nwere nyeere ha ọnọdụ site na wụnye agha na-enyocha n'ime obodo nke Slunfeh (ọwụwa anyanwụ Latakia Governorate), Masyaf (ọwụwa anyanwụ Tartous Governorate) na Ras Al-Bassit (Latakia ala gbara osimiri okirikiri obodo). [105] E nwekwara ndị ọzọ na-akọ nke ọhụrụ Iranian contingents na-deployed Syria na n'oge October 2015. [106] Ọ na-adịkarị chere na Iran ga-akpọ a na-eduga ọrụ na ala arụmọrụ nke Syria agha na allies, ebe Russia ga-eduga na ikuku na njikọ na Siria eefoosu, si otú guzosie ike a na-arụkọ ọrụ. [15]\n" +
"\n" +
"Na mpụga nke n'ọgbọ agha ya na Iranian ọchịchị n'ụzọ dị ịrịba ama ụba mgbalị ya na-akwado Siria ọchịchị na agha. Na nso nso e nwere kemgbe ihe exponential abawanye na mbanye mgbalị iji na-eru na Shi'ite militias na Iraq iji idokwa ha ka ha Syria. [23] Otu n'ime ndị na-eduga militias na Iran mgbalị iji wusikwuo mgbalị agha na Siria bụ Kata'ib al-Imam Ali, na Jaafar al-Bindawi, na militia si bụbu isi nke ọzụzụ na lọjistik, ga-eduga na nkenye ọnọdụ dị na Siria, mgbe Ali Nizam ga-eje ozi dị ka ọhụrụ logistical director maka Siria omume. Ọzọ dị mkpa na-ejikọ militia na a na-ekwu bụ Harakat Hezbollah al-Nujaba nke yiri Kata'ib al-Imam Ali e guzobere na Iranian enyemaka site tumadi mbụ Sadrist-akwado ma na alụso. E nwere amụba na-egosi na ndị a Iranian-kụziri militias na-abụghị nanị na-arụ ọrụ n'okpuru Iranian nduzi ma na-ekwenyere ndị Russian agha iwu guzobere na Syria. [23]\n" +
"\n" +
"On 8 October akụkọ wee na nke ọnwụ nke General Hamadani, ndị osote onye na ka General Qasem Soleimani na Syria. Ná mmalite ọ na-ekwu na ọ dara egbe-ọkụ si ISIL militants ke edere edere Aleppo ebe advising usu ndi nāpunara madu Mee-elu na na n'ihu. [107] Otú ọ dị ya mgbe e mesịrị merenụ na-akpata ọnwụ ya bụ onye na akpaaka-mobile mberede. [2 ] Ọ na-eche na ọnwụ ya ga budata oge n'ala-arụmọrụ ke edem edere Syria dị ka Hamadani bụ isi strategist na atụmatụ nke na-abịa arụmọrụ. [2]\n" +
"Syria: The Siria nnochite anya na Moscow, Riyad Haddad kwuru na Russian eefoosu na-eme na zuru nchikota na usuu ndị agha Siria. O kwukwara na Syria ọkwá bụ na Russian aka bụ nanị ziri ezi aka n'okpuru iwu mba, na-akpọ ndị ọzọ na mba iji sonyere ihe ọ kpọworo \"ndị na-abụghị mpụ\" Russian-ada aka na Syria. [75]\n" +
"\n" +
"International [edit]\n" +
"US-ada mmekota:\n" +
"\n" ;
        igboDocs.add(igboDocs1);
        igboDocs.add(igboDocs2);

        ArrayList<String> yorubaDocs = new ArrayList<>();
        String yorubaDoc1 = "Iji lile Joaquin jẹ ohun ti nṣiṣe extratropical olóoru lori awọn ariwa okun kariaye ti Atlantic Lọwọlọwọ idẹruba awọn Iberian ile larubawa. O ṣofintoto kikan tobi awọn ẹya ti awọn Bahamas ki o si laipe fowo Bermuda. O ni idamẹwa ti a npè ni iji, kẹta Iji lile ati keji pataki Iji lile ti awọn 2015 Atlantic Iji lile akoko. Atilẹba lati a ti kii-Tropical kekere, Joaquin a ti akọkọ classified a Tropical şuga on September 28, daradara oorun guusu ti Bermuda. Pẹlu lagbara afẹfẹ rirẹ-aloft awọn şuga lakoko ti gbiyanju lati teramo nigba ti gbigbe laiyara oorun guusu. Lẹhin ti di a Tropical iji, ọjọ kejì ni Joaquin lọ alakoso a ti dekun intensification lori gbona okun iwọn otutu pẹlu abating rirẹ-, nínàgà Iji lile ipo lori Kẹsán 30 Iji lile ati pataki agbara on October 1. Meandering lori awọn gusu Bahamas, Joaquin ká oju koja sunmo si tabi lori orisirisi awọn erekusu lori awọn wọnyi ọjọ meji. Lori October 3 awọn Iji weakened bikita ati onikiakia lati Ariwa. Abrupt re-aiyede ti intensification nigbamii ti ọjọ, ati Joaquin ti ipasẹ sustained "
                + "\fuufu ti 155 mph (250 km / h), seko awọn oniwe-tente agbara. Eleyi ṣe o Lágbára awọn Atlantic Iji lile niwon Iji lile Yalovecky ni 2010.\n"
                + "\n"
                + "Iji lile ikilo ni won dide kọja Elo ti awọn Bahamas on September 30 bi awọn Iji ewu awọn orilẹ-ede. Battering awọn orilẹ-ède ká erekusu gusu fun ju meji ọjọ, Joaquin ṣẹlẹ sanlalu devastation; Acklins, ayida Island, Long Island, Ọti Cay ati San Salvador Island sustained awọn buru bibajẹ. Awọn agbegbe won ti ya sọtọ ati ki o kù flooded fun orisirisi awọn ọjọ. Nsise awọn laisanwo omi El Faro lọ sonu pẹlu 33 atuko; lẹhin sanlalu wiwa awọn United States ni etikun Guard gbagbo awọn ha seese rì. Lẹyìn awọn weakening Iji lile koja kan oorun ti Bermuda, ìjìyà tí erekusu pẹlu lagbara efuufu ti o ṣẹlẹ agbara gige ati kekere bibajẹ.\n"
                + "Lori September 25, 2015, awọn US National Iji lile Centre (metadinlogun) bẹrẹ mimojuto ohun oke-ipele kekere, de pelu a dada trough, orisirisi awọn ọgọrun km guusu-oorun guusu ti Bermuda fun ṣee ṣe Tropical cyclogenesis. [2] The eto maa ti sọ di ọkan bi o ti drifted ariwa-Ariwa, ra a titi dada kekere pẹ lori Kẹsán 26. [3] Convective aṣayan iṣẹ-ṣiṣe-ojo ati thunderstorms-ni imurasilẹ pọ on Kẹsán 27, ati ni 03:00 UTC on September 28 metadinlogun iwon ni eto lati ti di a Tropical şuga, o le je ni aijọju 405 mi (650 km) oorun guusu ti Bermuda. [4] [5] [6] Biotilejepe awọn şuga ifihan a daradara-telẹ kekere, lagbara afẹfẹ rirẹ-nipo convection ati ki o fara awọn san. A Oke si ariwa ti a apesile lati da ori awọn eto laiyara Ariwa sinu a ekun ti o ga rirẹ-; meteorologists ni metadinlogun wa lakoko fihan awọn eto dissipating laarin wakati 96 da lori modeli depictions. [5] Convection ni idagbasoke ati ki o taku jo si san aarin jakejado September 28, [7] ati ki o tete lori Kẹsán 29 Dvorak satẹlaiti Oriṣi itọkasi awọn eto lati ti di a Tropical iji. O ti a yàn awọn orukọ "
                + "Joaquin accordingly, awọn idamẹwa ti a npè ni iji ti awọn akoko. [8] [9]\n"
                + "Okun ti awọn aarin-ipele Oke ọ a lojiji naficula ni Joaquin ká afokansi si guusu, darí o si ọna The Bahamas. [8] [10] forecasters ni metadinlogun woye akude aidaniloju ni ojo iwaju ti Joaquin, pẹlu apesile si dede depicting a jakejado ibiti o ti o ṣeeṣe. [11] Jakejado September 29 awọn iji ni imurasilẹ buru bi awọn oniwe-san di ifibọ laarin jin convection ati oke-ipele outflow di increasingly oguna. [12] High okun dada awọn iwọn otutu ati rirẹ-dinku o ràn okun, ati ki o tete lori Kẹsán 30 awọn iji waye Iji lile Ipo. [13] [14] Dekun intensification aiyede ti naa pẹlu ohun oju sese laarin a to dogba aringbungbun ipon overcast. Data lati ofurufu reconnaissance fihan wipe Joaquin ami Category 3 ipo lori awọn Saffir-Simpson Iji lile afẹfẹ asekale nipa 03:00 UTC on October 1. [15] Ni ayika 15:00 UTC awọn oju ti Joaquin kọja lori Samana Cay, Bahamas, pẹlu efuufu ti 125 mph (205 km / h). [16] Wakati nigbamii awọn eto siwaju buru si Ẹka 4 ipo, attaining o pọju sustained efuufu ti 130 mph (215 km / h), bi awọn oniwe-oju isunki lati 41 si 27 mi (66 si 43 km) ni opin. [17] [18] Ni akoko yi Joaquin a be o kan 15 mi (25 km) Ariwa ti wíwọ Island. [19] Awọn iji ká aringbungbun titẹ bottomed jade ni 931 mbar (hPa; 27,49 inHg) ni ayika 00: 00 UTC on October 2. [20]\n"
                + "\n"
                + "Bi awọn Oke tẹlẹ metalelogun ti Joaquin guusu bẹrẹ retreating ariwa, awọn Iji ká ronu slowed ati lo si-õrùn, ati nigbamii ariwa, on tete Oṣù 2. [21] [22] An eyewall rirọpo ọmọ-a ilana ti yoo mu a keji, o tobi oju ndagba nigba ti awọn akojọpọ oju-collapses bẹrẹ wipe owurọ, o [23] awọn oniwe-oju di increasingly aisan telẹ ni aworan satelaiti. [22] Iwonba weakening mu ibi accordingly, [24] ati awọn Iji kọja lori Ọti Cay ati San Salvador Island ni ayika 18:00 UTC ati 21:00 UTC bi a kekere-opin Ẹka 4 ati ki o ga-opin Category 3 lẹsẹsẹ; [25] a titẹ nitosi 944 mbar (hPa; 27,88 inHg) ti a woye on San Salvador Island. [26] An amplifying trough lori awọn Southeastern United States ti mu dara si southwesterly sisan lori Joaquin on October 3 ati ọ awọn Iji lati mu yara Ariwa kuro lati awọn Bahamas. [27] Jakejado awọn ọjọ awọn iji ká oju di increasingly telẹ ki o si tun-intensification aiyede ti. [28] Aircraft reconnaissance ri a ni riro ni okun eto ti Friday; da lori flight-ipele efuufu ti 166 mph (267 km / h), o ti wa ni ifoju-wipe Joaquin seese dada efuufu ti 155 mph (250 km / h) Olódì ga-opin Category 4 Iji lile-nipa 16:00 UTC. [29 ]\n"
                + "\n"
                + "Kó lẹhin peaking, awọn Iji ká ìwò be bẹrẹ lati deteriorate, lolobo a weakening aṣa. [30] Lori October 4 awọn iji te si ọna ariwa-Ariwa laarin kan ti o tobi kekere titẹ eto si awọn oniwe-oorun ati a aarin-ipele Oke si awọn oniwe-õrùn. [31] Bi jin convection lori awọn oniwe-mojuto tesiwaju lati wane, [32] Joaquin koja nipa 65 mi (105 km) ìwọ oòrùn-Ariwa ti Bermuda nitosi 00:00 UTC on October 5, ni Ẹka 2 kikankikan. [33] Awọn ase Advisory oniṣowo fun Joaquin wà ni owurọ ti October 8 nigbati o ti di a post-Tropical olóoru. [34]\n"
                + "Tropical-olóoru Agogo ati ikilo won Pipa jakejado awọn Bahamas o bere tete lori Kẹsán 30 (UTC); [35] nipa October 1, Iji lile ikilo tesiwaju lati sayin Bahama Island ni ariwaiwoorun si Mayaguana ni awọn Guusu. [36] Bi awọn iji ṣí kuro , awọn ti o kẹhin advisories won discontinued nipa awọn owurọ ti October 3. [37] Gbogbo ile-eko lori Exuma, Cat Island, San Salifado, ati Ọti Cay pipade lori awọn Friday ti October 1 titi siwaju akiyesi. [38] Bahamasair pawonre ọpọ abele ofurufu, [39] ati julọ papa jakejado orile-ede erekusu ni won ni pipade, ni isunmọtosi ni post-iji ojuonaigberaokoofurufu iyewo. [40] Olugbe on Mayaguana won niyanju lati evacuate. [41] Awọn orile-ede ile National pajawiri Management Agency (Ko) mu ṣiṣẹ awọn oniwe-pajawiri Operations Center. [ 42] Bi ipo worsened, olugbe erekusu ni gusu ti awọn Bahamas ti ṣofintoto ijoba fun pese inadequate Ikilọ, pẹlu ko si pajawiri ipalemo mu ibi on Acklins. Ko refuted awọn nipe ati ki o so eniyan ti won fun iwonba Ikilọ sugbon ọpọlọpọ awọn olugbe kọ lati evacuate. Ni diẹ ninu awọn instances, olopa won npe ni ni lati forcibly gbe awon eniyan lati dabobo. [43]\n"
                + "\n"
                + "Ni awọn Tooki ati Kaiko Islands si awọn Guusu ti awọn Bahamas, awọn iji fi agbara mu awọn Bíbo ti ile-iwe ati ijoba ifiweranṣẹ. [44] meji oko oju-omi won darí lati awọn erekusu, ati Providenciales International Papa ti daduro fun mosi fun akoko kan. [45] The erekusu won gbe labẹ a Tropical iji Ikilọ on October 1. [36] Lori October 2 (UTC), Tropical iji ikilo won hoisted pẹlú etikun Camagüey, Las Tunas, Holguín, ati Guantanamo Agbegbe ni Cuba. [46]\n"
                + "";

        String yorubaDoc2 = "ni 30 September 2015, Russia bere a ologun intervention ni Siria Ogun Abele. [59] O je ti air dasofo nipa Russia lodi si Ajagun awọn ẹgbẹ lodi si ijoba Siria, nipataki al-Nusra Front ati Jaysh al-Fatah (awọn Army ti Conquest) . [60] [61] [62] Russian ogun ní ilẹ significant support lati Iranian ilẹ enia si, awọn Siria ologun, ati Armies ti iti. [63] Šaaju si awọn intervention, Russian ilowosi ninu awọn ara Siria ti o kun Ogun Abele je ti arming awọn Siria Army. [64] Russia intervened lẹhin a lodo ìbéèrè lati awọn Siria ijoba fun ologun iranlọwọ lodi si ṣọtẹ ati jihadist ẹgbẹ ni Siria. [65] Ọpọ Russia ká airstrikes nigba awọn ọsẹ akọkọ ti awọn ipolongo lù agbegbe waye nipa awon olote ẹgbẹ lodi si awọn mejeji awọn Siria ijoba ati awọn ti Islam State ti Iraq ati awọn Levant. [66] [67] [68] [69] [70] [71]\n"
                + "\n"
                + "The United States, eyi ti o nlodi mejeji Islam Ipinle ati awọn Siria ijoba, ti pase jade ologun ifowosowopo pẹlu Russia ni Siria. [72] Akowe ti olugbeja Ashton Carter ati awọn miiran oga US ijoye wi Russia ká ipolongo ti a nipataki Eleto ni propping soke Assad, ẹniti Aare Barrack oba ma ti a npe ni lori leralera lati fi agbara, [67] ohun iwadi pín nipa France. [69] The US ti a ti yori a lọtọ ipolongo ni Siria niwon September 2014, bi daradara bi ọkan ninu Iraq niwon June 2014, mejeeji Eleto daada lodi si awọn Islam ati al Qaeda State amugbalegbe.\n"
                + "The Siria Ogun Abele ti wa ni a ó ja laarin ọpọ atako ati ki o ijoba awọn ẹgbẹ ati agbegbe won ati ajeji support ìtẹlẹ, ti o pin a ayipada, eka ayelujara ti kariaye-ibasepo. Bi orisirisi awọn osu ti air dasofo waiye nipasẹ awọn US-mu Iṣọkan ostensibly lodi si ISIL fojusi han lati ba ti yorisi ni siwaju imugboroosi ati okun ti awọn ISIL, [73] Russia, ohun ore ti Siria, ni September 2015 bere fifiranṣẹ awọn oniwe-ara Russian-ṣiṣẹ warplanes ati awọn miiran ologun hardware, bi daradara bi ija enia, si ohun airbase sunmọ awọn ibudo ilu ti Latakia ni Siria. [74] Awọn igbese ti a agbeyewo ni ohun osise ìbéèrè nipa awọn Siria ijoba ni ṣiṣi nipa Aare Bashar al-Assad. [75]\n"
                + "\n"
                + "Ni opin Kẹsán 2015 ti, a apapọ alaye ile-ni Baghdad a ṣeto soke nipa Iran, Iraq, Russia ati Siria lati ipoidojuko wọn mosi lodi si Islam State.";

        yorubaDocs.add(yorubaDoc1);
        yorubaDocs.add(yorubaDoc2);

        ArrayList<String> hausaDocs = new ArrayList<>();
        String hausaDoc1 = "Hurricane Joaquin ne wani aiki extratropical cyclone kan arewacin Atlantic Ocean a halin yanzu barazana da Yaren Kasar Larabawa. "
                + "Yana mai tsanani tasiri manyan sassa na Bahamas da kwanan nan ya shafa Bermuda. Shi ne ta goma mai suna hadari, na uku guguwa da na biyu manyan "
                + "guguwa na 2015 Atlantic guguwa kakar. Asali daga wani wadanda ba na wurare masu zafi low, Joaquin aka farko classified a wurare masu zafi ciki a "
                + "kan Satumba 28, da kudu maso yammacin na Bermuda. Tare da m iska karfi aloft da ciki da farko kokawa karfafa yayin da motsi sannu a hankali kudu maso "
                + "yammacin. Bayan zama na wurare masu zafi hadari gobe, Joaquin underwent wani lokaci na m intensification kan dumi teku yanayin zafi da abating karfi, "
                + "kai guguwa hali daban a kan Satumba 30 da kuma manyan guguwa ƙarfi a kan Oktoba 1. Meandering kan kudancin Bahamas, Joaquin ta ido wuce kusa da ko kan "
                + "da dama tsibiran a kan wadannan kwanaki biyu. A kan Oktoba 3 da raunana guguwa da ɗan da kara wa arewa maso gabashin. Ba zato sake intensification faru"
                + " wadda daga baya a wannan rana, da kuma Joaquin samu ci iskõki na 155 mph (250 km / h), constituting da ganiya ƙarfi. Wannan sanya shi da ƙarfi a"
                + " Atlantic guguwa tun Hurricane Igor a shekarar 2010.\n"
                + "\n"
                + "Hurricane gargadi aka tãyar da fadin da yawa daga cikin Bahamas a kan Satumba 30 a matsayin guguwa barazanar da kasar. Battering kasar kudancin tsibirin"
                + " ga kan kwana biyu, Joaquin ya sa m devastation. Acklins, m Island, Long Island, Rum Cay da San Salvador Island ci mafi mũnin lalacewa. Communities aka "
                + "bari ya zama ruwan dare da kuma flooded da dama kwana. Offshore da kaya jirgin El Faro ya bace tare da 33 ƙungiya. bayan m neman Amurka Coast Guard ya yi ĩmãni"
                + " da jirgin ruwa na iya nutse. Daga baya da weakening guguwa wuce kawai yammacin Bermuda, buffeting tsibirin tare da tsananin iska da ta haifar da iko cuts"
                + " da qananan lalacewa.\n"
                + "M iskõki kawo saukar da itatuwa da kuma mai amfani dogayen sanda a Rum Cay, clogging da hanyõyi. [65] A yawan gidajen da aka lalace ko halakar a gundumar."
                + " biyu Grocery Stores, kuma a birni dok kuma ci lalacewa. A coci gidaje 32 evacuees suka flooded da fasali mai damuwa, tilasta mazaunan kaura. [66] Acklins "
                + "ya jimre mai tsanani ambaliya, da yawa gidajen inundated da yawa da kira ga ceto. tsibirin ta teku shãmaki aka rushe ta 9:00 pm na gida lokaci. Wasu mazauna "
                + "ruwaito dukan tsibirin ya zama a karkashin ruwa. [43] A hadari rushe game da 20 gidajen on Acklins, da kuma wata gada a Lovely Bay aka gaba daya hallaka. "
                + " Tsara har zuwa 5 ft (1.5 m) zurfi kura idon a kalla 70 % na kusa da nan m Island, inda hadari ya bar tartsatsi tsarin lalacewa. [67] arba'in da shida gudun "
                + "hijira m Island mazauna, wasu bukata likita da hankali, aka hawa zuwa sama zuwa Sabuwar Providence a kan";
        String hausaDoc2 = "Da Boko Haram ya fara a 2009, [25] da jihadi 'yan tawayen kungiyar Boko Haram ya fara wani m tawaye da gwamnatin Nigeria. [26] A shekarar 2013, sama "
                + "da 1,000 suka mutu a rikicin. Da tashin hankali escalated da cika fuska a 2014, tare da 10.849 mutuwar. [20] [27] [28] [29] da tayar da kayar baya na tun baza"
                + " su Kamaru, Chadi, Nijar da kuma ta haka ne ya zama wata babbar yankin rikici.\n"
                + "\n"
                + "Tayar da kayar baya faruwa a cikin mahallin dogon tsaye al'amurran da suka shafi addini tashin hankali tsakanin Najeriya Musulmi da Kirista al'umma.\n"
                + "Najeriya An kamfanin Amalgamated biyu da Northern da kudancin protectoriate a shekara ta 1914, amma game da shekaru goma bayan da shan kashi na Sokoto "
                + "Khalifanci da sauran Musulunci jihohin da Birtaniya da suke zuwa dokoki ne da yawa daga Northern Nigeria. Sir Frederick Lugard, wanda ya dauki ofishin "
                + "kamar yadda gwamnan biyu protectorates a 1912. A aftermath na farko yakin duniya na gan Jamus rasa da mazauna, daya daga wanda ya Kamaru, to Faransa, "
                + "Belgium da kuma Birtaniya umurci. Kamaru aka raba a Faransa da kuma Birtaniya sassa, da karshen abin da aka kara subdivided cikin kudancin da kuma arewacin"
                + " sassa. Bin wani plebiscite a 1961, kudancin Cameroons zabe su sake haɗewa Faransa Kamaru, yayin da Northern Cameroons zabi ya shiga Najeriya, a tafi da "
                + "kara wa Nigeria ta riga manyan Arewa Musulmi yawan jama'a. [30] A Yankin kunsa da yawa daga abin da yake a yanzu arewa maso gabashin Najeriya, da kuma "
                + "babban ɓangare na yankunan shafi tayar da kayar baya.\n"
                + "Addini rikici a Nigeria ke har zuwa bãya a 1953. A Igbo kisan kiyashin na 1966 a Arewa da suka biyo da counter-juyin mulki na wannan shekara na da matsayin dual "
                + "hanyar da Igbo jami'an 'juyin mulki da kuma pre-data kasance (sectarian) tashin hankali tsakanin Igbos da kuma na gida Musulmi. Wannan shi ne mai babbar factor a "
                + "cikin Biafra ballewa da kuma sakamakonsa na yakin basasa.\n"
                + "Tun da dawowar dimokuradiyya a Najeriya a shekarar 1999, Sharia da aka kafa a matsayin babban jikin yakin da laifi doka a 9 musulmi-rinjaye da kuma a wasu sassa na "
                + "musulmi-jam'i jihohi, a lõkacin da sai-Zamfara Gwamnan Jihar Ahmad Rufa'i Sani [33 ] fara da tura ga ma'aikata na Sharia a jihar matakin gwamnatin. Wannan ya bi shawara a matsayin da zai kasance-shari'a matsayi na wadanda ba Musulmi a Sharia tsarin. A spate na musulmi-Christian tarzoma nan da sannu fito.\"";

        hausaDocs.add(hausaDoc1);
        hausaDocs.add(hausaDoc2);

        trainingCorpus.put("English", englishDocs);
        trainingCorpus.put("Igbo", igboDocs);
        trainingCorpus.put("Yoruba", yorubaDocs);
        trainingCorpus.put("Hausa", hausaDocs);

    }

    /**
     * gets the list of registered language category i.e English,Yoruba,Hausa,and Igbo
     * @return an array List of string type 
     */
    public ArrayList<String> getLanguageClasses() {
        return langClass;
    }

    /**
     * use {@link #getNumberOfLanguageCategories() }
     * to obtain the number of class of languages available from the training
     * corpus
     *
     *
     * @return integer value type
     */
    private int getNumberOfLanguageCategories() {
        return trainingCorpus.size();
    }

    /**
     * <p>
     * this helps to get the map that links each language category/class 
     * to its training corpus
     * <p>
     *
     *
     * @return a HashMap whose keys are the language category/class in string and values are Array List of training documents
     */
    public HashMap<String, ArrayList<String>> getTrainingCorpusMap() {
        return this.trainingCorpus;
    }

    /**
     *<>
     * the private method {@link #setNumberOfTrainingDocs} has been called internally to set this value
     * so it's safe to call {@link getNumberOfTrainingDocs() withour fear}
     *<>
     *@return   total number of training documents 
     */
    public double getNumberOfTrainingDocs() {
        return this.numberOfTrainingDocs;
    }

    private void setNumberOfTrainigDocs(int numOfDocs) {
        this.numberOfTrainingDocs = numOfDocs;
    }

    /**
     * <p>this helps to obtain the number of training documents in a language category/class<p>
     * @param specLangClass   
     * <p>this variable is used to specify the language category/class.
     *  the value specified is used as a key to get the arrayList of documents in that class,
     * then the size is computed and returned
     * <p>
     * @return the number of documents (integer type )in the specified class is returned as expected
     */
    public double getNumberOfDocsInClass(String specLangClass) {
        
        //using the training corpus key
        ArrayList<String> langClassList = trainingCorpus.get(specLangClass);

        System.out.println("The number of docs in " + specLangClass + " = " + langClassList.size());
        return langClassList.size();
    }

    /**
     * 
     * @param specLangClass 
     * <p> this variable is used to specify the language category<p>
     * @return  a concatenated string of all the hard coded training documents(in string format) for the specified language category
     */
    public String allDocsInClass(String specLangClass) {
        String docString = "";
        FeatureExtractor fe = new FeatureExtractor();

        Set keyObj = trainingCorpus.keySet();
        if (keyObj.contains(specLangClass)) {

            ArrayList<String> docList = trainingCorpus.get(specLangClass);
            ListIterator docListIterator = docList.listIterator();
            while (docListIterator.hasNext()) {
                docString += fe.callStemmer(docListIterator.next().toString().toLowerCase());
            }

        }

        return docString;
    }

    /**
     * <p>
     * This method simply helps to generate a vocabulary f all tokens(which is a set) 
     * from a collection of all the training documents(in string format)
     * <p>
     * @param trainingDocs  <p> uses the trainingDocs HashMap to generate the vocabulary
     * NOTE:trainingDocs is an HashMap that maps each language category(of string type) 
     * to an array list of training documents(of string type) belonging to each language class
     * <p>
     */
    
    public void buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs) {
        Iterator it = trainingDocs.keySet().iterator();
        FeatureExtractor fe = new FeatureExtractor();
        int numOfDocs = 0;

        while (it.hasNext()) {
            //looping through the training docs hashMap using the keys
            Object languageClass = it.next();//this takes the keys of the training docs HashMap..NB:Keys = LanguageClasses
            langClass.add(String.valueOf(languageClass));//adding to or registering languages...
            ArrayList<String> docsList = trainingDocs.get(languageClass);

            ListIterator listIt = docsList.listIterator();
            numOfDocs = numOfDocs + docsList.size();//gets and adds the size() of each doc in the ArrayList() 

            System.out.println("docsList = " + docsList);
            allDocsList.add(docsList);//populating the list of the documents lists in the training corpus HashMap
            while (listIt.hasNext()) {
                String docsString = listIt.next().toString();

                // String tokens[] = docsString.toLowerCase().replaceAll("[-(),'?.\"]", " ").split(" ");//splitting upon whitespace
                String tokens[] = fe.preProcess(docsString).split(" ");//splitting upon whitespace

                for (String token : tokens) {
                    String vocToken = fe.callStemmer(token);
                    vocabulary.add(vocToken);//adding to the vocabulary set...
                }
            }

        }

        setNumberOfTrainigDocs(numOfDocs);

    }
/**
 * this simply helps to update the vocabulary
 * @param textDocument 
 */
    public void updateVocabulary(String textDocument) {
        FeatureExtractor fe = new FeatureExtractor();
        String tokens[] = fe.preProcess(textDocument).split(" ");//splitting upon whitespace
        for (String token : tokens) {
            String vocToken = fe.callStemmer(token);

            vocabulary.add(vocToken);//adding to the vocabulary set...
        }
    }

    /**
     * Gets a list of all the documents
     * @return  a list of all the list of documents
     */
    public List<List<String>> getAllDocsList() {
        //a list of all the training documents
        return this.allDocsList;
    }

    /**
     * {@link #buildVocabulary(java.util.HashMap)} must have been called before calling this method
     * @return the vocabulary set generated from all the training documents
     */
    public Set<String> getVocabulary() {
        return this.vocabulary;
    }

    /**
     * Converts the vocabulary set {@link #getVocabulary()} into string format
     * @return a string format containing all the elements of the vocabulary set 
     */
    public String vocabularyToString() {

        String vocabString = "";
        Set<String> vocab = this.getVocabulary();
        Iterator vocabIterator = vocab.iterator();
        while (vocabIterator.hasNext()) {
            vocabString += " " + vocabIterator.next();
        }
        return vocabString;

    }

    /**
     * A utility method that simply returns the highest value from a collection of values of double data type
     * @param val    the specified collection of values
     * @return  the maximum...the greatest in the collection
     */
    public double highestValue(Collection<Double> val) {
     
        double valu = 0.0;
        double max = 0.0;
        Iterator it = val.iterator();

        while (it.hasNext()) {

            valu = Double.parseDouble(it.next().toString());
            if (max <= valu) {
                max = valu;
            }
        }

        return max;
    }

    /**
     * <p>this method simply helps to obtain the language category KEY from the probability HashMap
     * by specifying the VALUE of the probability
     * <p>
     * @param probMap a HashMap that stores the language categories KEYS and the probability values 
     * @param value specifies the probability value
     * @return the language KEY with the specified value
     */
    public String getPredictedLanguage(HashMap<String, Double> probMap, double value) {

        Double d = new Double("" + value + "");//converting the primitive double data type into the wrapper class
        String languageKey = null;

        for (Map.Entry entry : probMap.entrySet()) {
            if (d.equals(entry.getValue())) {
                languageKey = entry.getKey().toString();

                break; //break out because it is assumed to be a one to one map,no other value is assigned to such ket
            }
        }

        return languageKey;
    }

//    public static void main(String args[]) {
//
//        DataSet dt = new DataSet();
//        dt.loadTrainingData();
//        dt.buildVocabulary(dt.getTrainingCorpusMap());
//        System.out.println(dt.getAllDocsList());
//        // System.out.println("docs in english= " + dt.allDocsInClass("English"));
//        
//    }
}
