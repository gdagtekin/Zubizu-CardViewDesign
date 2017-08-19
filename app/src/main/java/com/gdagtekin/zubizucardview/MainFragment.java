package com.gdagtekin.zubizucardview;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gdagtekin.zubizucardview.Cardview.DerpAdapter;
import com.gdagtekin.zubizucardview.Cardview.DerpData;
import com.gdagtekin.zubizucardview.Cardview.ListItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements DerpAdapter.ItemClickCallback {
    private RecyclerView recView;
    private DerpAdapter adapter;
    private ArrayList listData;

    //Bundle için sabitler
    private final static String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private final static String BUNDLE_TEXT = "BUNDLE_TEXT";
    private final static String BUNDLE_TITLE = "BUNDLE_TITLE";
    private final static String BUNDLE_IMAGE = "BUNDLE_IMAGE";
    private final static String BUNDLE_FOLLOW = "BUNDLE_FOLLOW";

    private String[] images = {
            "https://i.hizliresim.com/LnLPEb.png",
            "https://i.hizliresim.com/ok1Qp9.png",
            "https://i.hizliresim.com/mknlL2.png",
            "https://i.hizliresim.com/N17VEk.png",
            "https://i.hizliresim.com/dGRjEp.png",
            "https://i.hizliresim.com/RPqy3o.png",
            "https://i.hizliresim.com/nWBPoN.png",
            "https://i.hizliresim.com/ERZlJD.png",
            "https://i.hizliresim.com/DdaXJy.png",
            "https://i.hizliresim.com/jWoJng.png",
            "https://i.hizliresim.com/DdaXr3.png",
            "https://i.hizliresim.com/YNAPbD.png",
            "https://i.hizliresim.com/8dJn1d.jpg",
            "https://i.hizliresim.com/gWBlD0.png",
            "https://i.hizliresim.com/aG6qEd.png"

    };

    public String[] title = {
            "Alışveriş öncesi tavsiyeleri",
            "Sıcaktan Korkmayan Siyahlar",
            "Plastik Devri",
            "Baklava Desenini Yeniden Keşfedin",
            "Tek Renklilik",
            "2017 yılının abiye trendleri",
            "Şile Bezi vs. Keten",
            "Şehirde Yaz Başkadır: Tatlı Pötikare",
            "Nedimeler için kıyafet önerileri",
            "Sıcak havalar için giysi önerileri",
            "Yaz mevsiminde ayaklarınızı mutlu etmek için tavsiyeler",
            "Damat-gelin uyumu için ipuçları",
            "Emili Sindlev'dan Desen ve Renklerin Farklı Bir Yorumu",
            "Yaz sezonunun en romantik trendi: Fırfır",
            "Beyaz Pantolonları Nasıl Kullanmalı?"
    };


    public String[] text = {
            "Alışverişe çıkmadan önce mutlaka gardırobunuza bir göz atın. Elinizde olan kıyafetleri bilmek ve ona göre yapılan alışveriş her yönden size artı getirecektir. Gardırobunuzdaki stoğu bilmek alışveriş esnasında size büyük kolaylık sağlar. Dolayısıyla evden çıkmadan önce, kafanızda bir alışveriş listesi hazırlayın. Fakat listeye eklediklerinizin gerçekten ihtiyaç mı yoksa geçici istekler mi olduğuna dikkat edin. Bu ikisi arasında büyük bir fark var. “Buna gerçekten ihtiyacım var mı?” diye sorun. Çoğumuz bazen düşünmeden, adeta bir refleks olarak alışveriş yapıyor, istemediğimiz ya da ihtiyacımız olmayan şeyleri alıyoruz. Bu durumdan kurtulmanın yollarından biri, almayı düşündüğünüz şeye sahip olduğunuzu hayal etmektir. O şeyin sizin olduğunu, evinizde ya da dolabınızda durduğunu düşünün. Bu sizi heyecanlandırıyor mu? Yoksa artık o kadar parlak gözükmüyor mu? Kararınızı bu şekilde vermeye çalışırsanız, ihtiyacınız olmayan şeylere paranızı ve vaktinizi harcamamış olursunuz.",
            "Havalar ısınıp güneş tepede belirince, siyahların inzivaya çekileceğini düşünüyorsanız bu defa fena yanılıyorsunuz. Sezonun yazlık siyahları güneşten korkmuyor; Hafif kumaşlarıyla sıcağa, modern kesimleriyle ise en havalı beyazlara kafa tutuyor.\n" + "Çok sevdiğiniz siyah elbisenizi giydiniz ancak çok mu karamsar bir kombin yaptım diye endişeleniyorsanız çanta, ayakkabı gibi daha büyük aksesuarları can simidi olarak kullanabilirsiniz. Metalik pudra bir ayakkabı ya da cici bir çanta ile kombininize renk katabilirsiniz. Önemli olan siyah kıyafetlerde renk uyumunu yakalayabilmek. Aksesuarlar içinse anahtar kelime her zamanki gibi \"ölçü\".",
            "New York, Londra, Milano ve Paris Moda Haftası'nda kendini açık eden 2017-18 Sonbahar/Kış trendleri, yenilikçi bir sezonun habercisi. Sezonun en ilginç kabul edilen trendi şüphesiz plastik, pvc , şeffaf maddeden yapılan giysi ve aksesuarlar. Bu sezon tasarımcılar şeffaf tasarımların izinde. Ayakkabıdan çantaya, şapkadan tokaya sıçrayan plastik etkisi, Loewe, Vetements ve Christopher Kane koleksiyonlarında kıyafetleri de etkisi altına alıyor. Kıyafetlerini kapamadan sıcaklığı yükseltmenin en stil sahibi yolunu bulan sezon kadını katman yarışını önde kapıyor.",
            "Geçtiğimiz sezon yüksek modada köşeli siluetlerle başlayan köşe kapmaca oyunu, Pre-Fall koleksiyonlarında karolar ve karelerle devam ediyor. Baştan aşağı baklavalara teslim olan sezon kadını, nihayet nostaljik çağrışımlar yapan bu tanıdık desenin hakkını vermeye hazır. Karolar, Stella McCartney Pre-Fall 2017 koleksiyonunda sarı, turuncu, kırmızı ve mavinin baskın tonlarıyla konforlu parçaları yoldan çıkarıyor. Pringle of Scotland’in naif renk skalasında ise dingin görünümlerini koruyor.",
            "Hangi kadın her zaman modern ve şık görünmek istemez. Ancak bunun zahmetli bir iş olduğunu düşünüyorsanız yanılıyorsunuz. Çok küçük detaylara dikkat ederek siz de her daim şık olabilirsiniz.\n" + "Modacılar her yıl favori olan kıyafetleri, ayakkabıları ve aksesuarları belirlese de şu gerçeği unutmamalısınız; her kadın kendisinin modacısıdır. Ancak kendi tarzınızı yaratırken de modanın hiçbir zaman değişmeyen kurallarına dikkat etmekte fayda var.\n" + "Tek renklilik, kurtarıcınız olabilir. Tepeden tırnağa aynı renkte giysiler giyerseniz, uzun, bölünmemiş bir çizgi illüzyonu yaratmış olursunuz. Bu da sizi daha ince gösterir ve kusurların daha az göze çarpmasını sağlar. Siyah, devetüyü, krem, koyu kahve gibi nötr tonları kullanmayı tercih edin.",
            "Geçtiğimiz sezon abiye modasında çiçekli desenler çokça kullanılmıştı. Yeni sezonda bu desenler devam etse de kendini doğanın farklı desenlerinden esinlenen modellere bırakıyor. Kumaşlar dümdüz tek renk kullanıldığı kadar, doğada yer alan değişik desenleri de içinde barındırmaya devam ediyor. Geçtiğimiz yıllarda canlı renkler daha çok revaçtayken 2017 yazında daha soft ve yumuşak renkler ön planda. Pastel pembe, mavi, somon ve sarı renkler yeni sezonun gözde renkleri arasında. Uzun süredir abiye elbiselerde uzun modeller tercih ediliyordu. Ancak 2017 yazında daha kısa, diz altında biten, kalem etek tabir edilen modeller de yeni sezonun gözdeleri arasında yer alıyor. \n" + "Yeni trend abiyelerde V yakalar ve volanlı kollar sıkça kullanılıyor. Vintage tarza dönüş yapan abiyeler, pek çok davet, organizasyon ve düğünlerin vazgeçilmez modelleri arasında yerini alacak. Taş, payet, boncuk gibi detaylar abiyelerde ufak ufak yer alsa da daha sadeleşen modeller ön planda. \n",
            "Kalın ve terleten kumaşlara tahammülümüz yok. Vücuda yapışmayan havadar parçaların izindeyiz! \n" + "Şile Bezi:\n" + "Havaların ısınmasıyla bir işkenceye dönüşen giyinme aktivitesi, hafif parçaların popülaritesini katlıyor. Ferah tasarımlara hayat veren tanıdık kumaşlar bir anda cool mertebesine ulaşıyor. Acne Studios İlkbahar/Yaz 2017 şovundaki beklenmedik çıkışıyla dikkatleri üzerine çeken şile bezi, bu kumaşların başında geliyor. Bu yaz İskandinav minimalizminden sıyrılarak geometrik motifler ve tribal desenlerin çekim alanına giren Acne tasarımları bu kumaşın etnik yüzünü açığa çıkarırken Loewe yalın ama sıradanlıktan uzak çizgisini koruyor.\n" + "Keten:\n" + "Çabuk kırışmasıyla zaman zaman insanı zor durumda bıraksa da vaat ettiği çabasız şıklık ve görünüme getirdiği ferahlık onu her daim vazgeçilmez kılıyor. Keteni hayatından çıkaranlarsa, bu yaz koleksiyonunu neredeyse baştan sona ketene adayan J.W. Anderson’ın degrade elbiselerini ve Jacquemus’ün ekstra cool tasarımlarını gördükten sonra kararını tekrar gözden geçirmek isteyebilir.\n",
            "Gömleklerde, ayakkabılarda ya da çantalarda; siyah, kırmızı ya da diğer renklerde! Pötikarenin uçsuz bucaksız olasılıklarla karşımıza çıktığı bir sezonda yaza ayak uydurmak ne kadar zor olabilir ki? Hiç çaba harcamadan üzerinize bir elbise geçirebilir veya pötikare parçaları denimle eşleştirerek cool duruşu garantileyebilirsiniz. Siyah beyaz renk olanları en klasik modellerden, bu parçaları yine siyah beyaz parçalarla tamamlayabilirsiniz. Daha sade ve asil bir görünüm elde edebilirsiniz. Altın ya da gümüş detaylarla da taçlandırabilirsiniz. Biraz daha renkli bir formül arayanlara ise önerimiz pembe-beyaz, sarı-beyaz, lacivert-beyaz gibi farklı renkteki pötikare modeller. Bu renkli parçaları da yine canlı renklerde ayakkabı ve çantalarla tamamlayarak daha yaza uygun kombinler yapabilirsiniz.",
            "Yaz mevsiminin gelmesi ile birlikte düğün sezonu ve telaşı başladı. Gelinin en yakın arkadaşlarından ve akrabalarından oluşan nedimeler, düğün öncesindeki aktivitelerde ve düğün gününde gelinin bir nevi yardımcısıdır. Bunun yanı sıra, ülkemizde de son zamanlarda yaygınlaşan nedime trendinde nedimeler, düğün konseptine ve gelinin isteklerine göre isteğe göre farklı veya aynı renk kıyafetlerle o özel günü süsler.  Son zamanlardaki eğilime göre nedimelerin, düğünde bütünlüğü sağlaması için genellikle aynı renk kumaş kullanılarak hazırlanan farklı kesim ve modelde elbiseler giydiklerini görüyoruz. Nedime elbiseleri için renk seçimi genellikle düğün konseptinde ve gelinin aksesuarlarında kullanılan renk uyum sağlayacak şekilde oluyor. Örneğin gelin çiçeği pembe ise, nedime kıyafetleri de pembe renk hazırlanıyor.\n" + " Nedimeler, bu sene çiçek açan kıyafetler giyerek tazelenen bir imaj veriyor. Senenin trendi olan soft renkler ve çiçekler nedime elbiselerinde en çok tercih edilen trendlerden. \n",
            "Yaz aylarında aşırı sıcak havalarda güneşin zararlı ışınlarına maruz kalmamak ya da en basitinden bunalmamak için giydiğimiz kıyafetlerden beslenmemize kadar daha bir çok şeye dikkat etmeliyiz, örneğin;\n" + "Daha çok teri dışarı atan pamuklu giysiler tercih etmeliyiz. Sentetik ve polyester kumaşlardan yapılan giysiler daha çok terlemeye, terin kokmasına, vücutta bakteri oluşumuna ve bir süre sonrada mantar enfeksiyonu oluşumuna neden olabilmektedir. Güneşin zararlı ışınlarından korunmak içinde güneş ışınlarını kırarak geçirgenliği engelleyen ya da geçirgenlik oranını en aza indiren renklerdeki (beyaz, sarı, mavi, yeşil gibi )  giysileri tercih etmek sağlık için en doğru seçim olacaktır.\n" + "Ayrıca aşırı sıcak havalarda deri ya da vücudu çok saran kıyafetler yerine daha rahat ve bol giysiler tercih etmek cilt sağlığı açısından önemlidir. Zira; bakteriler en çok sıcak alanlarda ürediği için vücudunuzda bakterilere de bir yer ayırarak onları mutlu etmek yerine sağlığınızı korumak adına tüm önlemlerinizi almak zorundasınız.\n",
            "Güneşli havalarda giysilerinize olduğu kadar ayakkabılarınızın seçimine de özen göstermelisiniz. Yaz ayları için ayakkabı seçerken ayağı arkadan ve önden kapatan ayakkabılar yerine ayağın hava alması için burnu açık veya sandalet tarzında ayağı birçok yerden havalandıran ayakkabılar tercih edilmelidir. Çünkü aşırı sıcaklarda terlemeyle beraber ayaklarda oluşan bakteriler, rahatsız edici kokulara ve çeşitli enfeksiyonlara neden olabilmektedir. Bunun önüne geçebilmek için hava alabilen, sağlıklı kumaşlara sahip olan ayakkabılarınıza öncelik verebilirsiniz. \n" + "Kapalı ayakkabı giymekte kararlı iseniz, ayakkabınızı giymeden önce içerisine biraz bebek pudrası serperek nemlenmeyi önleyebilmeniz mümkün. Gün sonunda evinize döndüğünüzde ise ayakkabınıza, ayakkabılar için özel olarak hazırlanan deodorantlardan sıkarak oluşmuş olan bakterileri yok edebilirsiniz. Ayrıca işiniz bittikten sonra ayakkabılarınızı kaldırırken içlerine birer tane poşet çay koyarsanız tekrar kullanmak istediğinizde, ayakkabılarınızın hafif ve güzel bir çay kokusuna sahip olduklarını göreceksiniz.\n",
            "Aylarca hatta belki de yıllarca beklediğiniz düğün törenine sayılı günler kaldı. Her şey muhteşem görünsün diye olağan üstü bir çaba gösterdiniz. Hayatınızın en özel aynı zamanda en heyecanlı günü için hazırlanmanın hiç de kolay olmadığını biliyoruz. Fakat karşılığında ömrünüz boyunca unutmayacağınız bir gün yaşayacaksınız, işte bunun için her şeye değersize vereceğimiz ufak tavsiyeleri uygulayarak böylesine güzel bir günü uyumlu bir çift olarak tamamlayabilirsiniz.\n" + "Damat ve gelin bütünlüğü için benzer aksesuarları tercih etmek oldukça iyi bir çözümdür. Damat, yakasına çiçek takacaksa gelin buketindeki çiçeği tercih edebilir. Eğer gelinin takı tercihi beyaz altından ve pırlantadan yanaysa o zaman damat da kol düğmelerinde, kravat aksesuarlarında beyaz altın rengine yönelebilir.\n" + "Damat beyaz bir takım giyecekse gelinliğin de aynı renkte olmasında fayda var. Zira beyazın yanında ekru tonlar biraz kirli görünebilir. Belki bu durum gerçekte gözü yormaz ama fotoğraf çekimlerinde belli olur\n",
            "Farklı renk ve desenleri bir arada kullanmayı, resmi tasarımlarla konforlu parçaları aynı siluette harmanlamayı fazla deneysel buluyorsanız, bir de Emili Sindlev üzerinde görün. Kopenhag Moda Haftası boyunca renkli ve dinamik stiliyle dikkat çeken it-girl, ders niteliğindeki karıştırma alışkanlıklarıyla karşınızda.\n" + "1.\tNeon renkler ve pastel tonları bir arada kullanın:\n" + "Neon renkler, en sakin tavrını pastel parçalarla buluştuğu naif siluetlerde sergiliyor. Siz de görünümünüze entegre edeceğiniz renkli bir aksesuarla minimal bir etki yaratabilir ya da Emili Sindlev gibi mini neon çantanızı üzerinizdeki bir parçanın baskın desenleriyle eşleştirerek etkiyi katlayabilirsiniz.\n" + "2.\tFeminen ve maskülen desenleri harmanlayın:\n" + "Bu sezon tarafınızı seçmek zorunda değilsiniz. Hem feminen hem maskülen desenlere aynı mesafede durabilirsiniz. Ekoseli jilenizin içine floral desenli gömleğinizi giyebilir, çiçekli elbisenizin üzerine attığınız ekoseli paltonuzla dışarı çıkabilirsiniz.\n" + "3.\tResmi Tasarımlar ve Konforlu Parçalar\n" + "Ciddi tasarımlar, rahat ve salaş parçalarla flörtte. Kalem etek-blazer ikilisi içine tişörtleri, jean-tişört ikilisi üzerine blazer'ları alıyor. Hem konforlu hem resmi giysiler Emili Sindlev'in üzerinde sokağa beraber çıkıyor.\n",
            "Son aylarda mağazalara, online alışveriş sitelerine, moda dergilerine bir göz attıysanız en çok gözünüze çarpan detaylardan birisi şüphesiz fırfırlar olmuştur. Kollarda, bellerde, etek uçlarında derken fırfırlar artık her yerde. Çantalarda ve ayakkabılarda dahi bu romantik detaya rastlayabilirsiniz.\n" + "Feminen, retro ve dramatik fırfır detayları yazın favori trendi olmasının yanı sıra bizim için işleri oldukça kolaylaştırıyor. Farklı stillere kolayca adapte edebileceğimiz gibi çabasız şıklığa bir altın dokunuş adeta.\n" + "Sokak stilini birkaç level yükseltecek bir detaydan bahsediyoruz. Fırfır tek başına yeterince dramatik olduğu için fazla bir şeye ihtiyaç duymazsınız. Bu yüzden fırfırların yakınında aksesuar kullanmak iyi bir fikir değil. Bir kot pantolon veya bir kot etekle giydiğinizde bile yeterince gösterişli olabilirsiniz.\n" + "Peki fırfırlı giymenin bir kuralı yok mu? Tabii ki var. Fırfırlı bir giysi tercih edecekseniz dikkat etmeniz gereken ilk ve en önemli kural: Vücudunuzun hangi kısmına dikkat çekmek istemiyorsunuz. Fırfır vücudunuzun hangi bölgesindeyse o bölgeye volüm ve hareket katacak. Geniş omuzlu biriyseniz omuzları fırfırlı bir bluzla genişliği iki katına çıkarmak istemezsiniz. Ya da armut vücut yapısına sahipseniz belden aşağıda fırfır kullanmasanız iyi edersiniz.\n",
            "Her kadının beğendiği, üzerinde görmek istediği beyaz pantolon tarzları vardır. Ancak beyaz pantolon tercih ederken üzerinizde nasıl kombinleyeceğinizi uzun uzun düşünürsünüz. Zor bir parça olan beyaz pantolonun sizi olduğumuzdan daha kilolu, fazla iddialı göstereceğini düşünerek tercih etmekte zorlanırsınız.\n" + "Evet, beyaz pantolonlar karar vermesi zor parçalardır. Ancak doğru pantolon ve doğru kombin ile birlikte şık ve elit bir görüntüyü rahatlıkla yakalayabilirsiniz.\n" + "Boyunuzu daha uzun göstermek istiyorsanız pantolonunuzu yüksek bel beyaz pantolon seçmelisiniz. Ancak kastedilen yükseklik, bel kıvrımlarınızı aşacak şekilde olmamalı. Çünkü bu tercihte sanıldığının aksine zayıf değil kalın belli gözükürsünüz. Ayrıca bu durum üstünüzü daha kısa göstereceğinden vücudunuz orantısız duracaktır.\n" + "Kalçalarınızdan memnun değilseniz seçeceğiniz beyaz pantolonların üzerine kalçanızı kapanacak bol tişörtler tercih edebilirsiniz. Böylece daha cool bir görünüme sahip olabilirsiniz.\n" + "Daha ince görünmek için yanlarında siyah uzun şeritleri olan beyaz pantolonlar tercih edebilirsiniz.\n" + "Hem cool hem de iddialı olmak istiyorum diyorsanız beyaz pantolon üzerine beyaz blazer ceketler tercih edebilirsiniz. İçinize giyeceğiniz pastel renkli bir body ile tüm dikkatleri üzerinize çekebilirsiniz.\n" + "Stiletto her kadının kurtarıcısıdır. Yine beyaz dar paça pantolonlarınızın altına rengarenk stilettolar tercih edebilirsiniz. Ancak dikkat etmeniz gereken unsur kombinleyeceğiniz bluz ya da tişörtler olacaktır. Stilettonuzu renkli seçecekseniz üzerinize daha sade bir tişört ya da bluz seçmeniz yararınıza olur.\n"
    };

    public String[] shortText = {
            "Alışverişe çıkmadan önce mutlaka gardırobunuza bir göz atın. Elinizde olan kıyafetleri bilmek ve ona göre yapılan alışveriş her yönden size artı getirecektir...",
            "Havalar ısınıp güneş tepede belirince, siyahların inzivaya çekileceğini düşünüyorsanız bu defa fena yanılıyorsunuz...",
            "New York, Londra, Milano ve Paris Moda Haftası'nda kendini açık eden 2017-18 Sonbahar/Kış trendleri, yenilikçi bir sezonun habercisi...",
            "Geçtiğimiz sezon yüksek modada köşeli siluetlerle başlayan köşe kapmaca oyunu, Pre-Fall koleksiyonlarında karolar ve karelerle devam ediyor...",
            "Hangi kadın her zaman modern ve şık görünmek istemez. Ancak bunun zahmetli bir iş olduğunu düşünüyorsanız yanılıyorsunuz...",
            "Geçtiğimiz sezon abiye modasında çiçekli desenler çokça kullanılmıştı. Yeni sezonda bu desenler devam etse de yerini farklı desenlere bırakıyor...",
            "Kalın ve terleten kumaşlara tahammülümüz yok. Vücuda yapışmayan havadar parçaların izindeyiz...",
            "Gömleklerde, ayakkabılarda ya da çantalarda; siyah, kırmızı ya da diğer renklerde...",
            "Yaz mevsiminin gelmesi ile birlikte düğün sezonu ve telaşı başladı...",
            "Yaz aylarında aşırı sıcak havalarda güneşin zararlı ışınlarına maruz kalmamak ya da en basitinden bunalmamak için giydiğimiz kıyafetlerden...",
            "Güneşli havalarda giysilerinize olduğu kadar ayakkabılarınızın seçimine de özen göstermelisiniz...",
            "Aylarca hatta belki de yıllarca beklediğiniz düğün törenine sayılı günler kaldı. Her şey muhteşem görünsün diye olağan üstü bir çaba gösterdiniz...",
            "Farklı renk ve desenleri bir arada kullanmayı, resmi tasarımlarla konforlu parçaları aynı siluette harmanlamayı fazla deneysel buluyorsanız...",
            "Son aylarda mağazalara, online alışveriş sitelerine, moda dergilerine bir göz attıysanız en çok gözünüze çarpan detaylardan birisi şüphesiz fırfırlar olmuştur...",
            "Her kadının beğendiği, üzerinde görmek istediği beyaz pantolon tarzları vardır..."
    };

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        listData = (ArrayList) DerpData.getListData();
        recView = (RecyclerView) root.findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DerpAdapter(listData, getContext());
        recView.setAdapter(adapter);
        adapter.setItemClickCallback(this);
        adapter.setListData(listData);


        return root;
    }

    @Override
    public void onItemClick(int p) { //Cardviewe tıklanma
        ListItem item = (ListItem) listData.get(p); //Tıklanan itemin verileri alınıyor
        Intent i = new Intent(getActivity(), DataActivity.class);


        //Alınan veriler yeni activity için bundle ekleniyor
        Bundle extras = new Bundle();
        extras.putString(BUNDLE_TITLE, item.getTitle());
        extras.putString(BUNDLE_TEXT, item.getText());
        extras.putString(BUNDLE_IMAGE, item.getImage());
        extras.putBoolean(BUNDLE_FOLLOW, item.isFollow());
        i.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(i);
    }

    @Override
    public void onFollowIconClick(int p) {
        //Takip et butonuna tıklanma

        ListItem item = (ListItem) listData.get(p);
        if (item.isFollow()) {
            Toast.makeText(getActivity(), item.getTitle() + " takipten çıkarıldı.", Toast.LENGTH_SHORT).show();
            item.setFollow(false);
        } else {
            Toast.makeText(getActivity(), item.getTitle() + " takip edildi.", Toast.LENGTH_SHORT).show();
            item.setFollow(true);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        for (int i = 0; i < title.length; i++) { //Verileri ListItem a ekleme
            ListItem item = new ListItem();
            item.setTitle(title[i]);
            item.setShortText(shortText[i]);
            item.setText(text[i]);
            item.setImage(images[i]);
            item.setFollow(false);
            listData.add(item);
            adapter.notifyItemInserted(listData.indexOf(item));
        }
    }
}
