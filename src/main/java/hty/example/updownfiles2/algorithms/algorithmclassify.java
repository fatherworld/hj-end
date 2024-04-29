package hty.example.updownfiles2.algorithms;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;
import java.io.*;
import java.util.HashMap;
import java.util.Vector;
public class algorithmclassify {
    //目前是输入一个文件
    public algorithmclassify(String algorunfile,String algorunparas,String alogconfigfilepath)
    {
        this.alogconfigfilepath = alogconfigfilepath;
        this.algorunfile = algorunfile;
        this.algorunparas = algorunparas;
        try {
            Boolean res = parseJson();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }
    public void setFilepath(String alogconfigfilepath) {
        this.alogconfigfilepath = alogconfigfilepath;
    }
    //输入的文件路径
    private String alogconfigfilepath;
    private String algorunparas;
    private String algorunfile;
    //              分类维度       任务载体          算法应用系统      算法应用载体    智能算法产品举例
    private HashMap<String,HashMap<String,HashMap<String,HashMap<String,      Vector<String>>>>> parsealgofileresult;

    //              智能算法产品        算法应用载体                    算法应用系统     任务载体
    private HashMap<String,           HashMap<String,      HashMap<String,          String>>> parsealgoclassresult;

    private Boolean parseJson() throws IOException {
        File file = new File(alogconfigfilepath);
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString();
        //解析第一层对象
        ObjectMapper mapper=new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonStr);
        //HashMap<String,HashMap<String,HashMap<String, HashMap<String,       Vector<String>>>>> algoclassType = new HashMap<String,HashMap<String,HashMap<String, HashMap<String,       Vector<String>>>>>();
        JsonNode algoclassfyroot = rootNode.get("algorithmclassify");
        for(int i=0;i<algoclassfyroot.size();++i)
        {
            JsonNode DimensionNode = algoclassfyroot.get(i);
            if(DimensionNode.has("Dimension"))
            {
                String dimension = DimensionNode.get("Dimension").toString();
                HashMap<String,HashMap<String, HashMap<String,       Vector<String>>>>  taskcarryer = new HashMap<String,HashMap<String, HashMap<String,       Vector<String>>>>();
                JsonNode taskcarryerNode = DimensionNode.get("taskcarryer");
                HashMap<String,      HashMap<String,          String>> rtaskcarryer = new HashMap<String,      HashMap<String,          String>>();
                for(int j=0;j<taskcarryerNode.size();++j)
                {
                    JsonNode signalCarryer = taskcarryerNode.get(j);
                    String carryer = signalCarryer.get("carryer").toString();
                    if(signalCarryer.has("algoapptype")) {
                        JsonNode signalAppType = signalCarryer.get("algoapptype");
                        HashMap<String, HashMap<String, Vector<String>>> algoapptype = new HashMap<String, HashMap<String, Vector<String>>>();
                        HashMap<String,          String> ralgoapptype = new HashMap<String,          String>();
                        for (int k = 0; k < signalAppType.size(); ++k) {
                            JsonNode typeNode = signalAppType.get(k);
                            String type = typeNode.get("type").toString();
                            //System.out.println("type:"+type);
                            if(typeNode.has("algoappcarryer")) {
                                JsonNode appcarryerNode = typeNode.get("algoappcarryer");
                                HashMap<String, Vector<String>> percepprocessNode = new HashMap<String, Vector<String>>();
                                for (int p = 0; p < appcarryerNode.size(); ++p) {
                                    JsonNode LeftNode = appcarryerNode.get(p);
                                    String percepprocesstype = LeftNode.get("percepprocesstype").toString();
                                    //System.out.println("percepprocesstype:"+percepprocesstype);
                                    JsonNode intellijalgoproducts = LeftNode.get("intellijalgoproduct");
                                    Vector<String> algoproducts = new Vector<String>();
                                    for (int q = 0; q < intellijalgoproducts.size(); ++q) {
                                        algoproducts.add(intellijalgoproducts.get(q).toString());
                                    }
                                    percepprocessNode.put(percepprocesstype, algoproducts);
                                }
                                algoapptype.put(type, percepprocessNode);
                            }
                            //System.out.println("type finished");
                        }
                        taskcarryer.put(carryer, algoapptype);
                    }
                }
                parsealgofileresult.put(dimension,taskcarryer);
            }
        }
        return true;
    }
    //根据输入的算法运行文件和算法运行参数进行算法类型的分类
    private String classfiyType()
    {
        String Type = "";


        return Type;
    }
}
