import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo04_Susubstring_IsSpecialChar {
    @Test
    public void test() {
//        String xml = "";
//        xml = "cderbdverd<DataSet><?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<pensiondata>\n" +
//                "\t<version>1.0.0</version>\n" +
//                "\t<sendcode>T14</sendcode>\n" +
//                "\t<receivecode>S50</receivecode>\n" +
//                "\t<sendername>中国人民养老保险有限责任公司</sendername>\n" +
//                "\t<receivername>重庆社保</receivername>\n" +
//                "\t<senddate>20201112</senddate>\n" +
//                "\t<sendtime>153420</sendtime>\n" +
//                "\t<dgst></dgst>\n" +
//                "\t<datasettype>\n" +
//                "\t\t<typecode>1217</typecode>\n" +
//                "\t\t<typename>受托户资产负债表</typename>\n" +
//                "\t</datasettype>\n" +
//                "\t<pensioninfo>\n" +
//                "\t\t<appseriono>20201112T141217000000007</appseriono>\n" +
//                "\t\t<transtype>01</transtype>\n" +
//                "\t\t<planid>T14</planid>\n" +
//                "\t\t<planname>重庆市捌号职业年金计划</planname>\n" +
//                "\t\t<planlicid>5000ZY201908</planlicid>\n" +
//                "\t\t<itemcode>0101</itemcode>\n" +
//                "\t\t<itemname>银行存款</itemname>\n" +
//                "\t\t<begbalance>0.00</begbalance>\n" +
//                "\t\t<endbalance>0.00</endbalance>\n" +
//                "\t\t<statementdate>20201028</statementdate>\n" +
//                "\t\t<startdate>20201001</startdate>\n" +
//                "\t\t<enddate>20201231</enddate>\n" +
//                "\t\t<currency>156</currency>\n" +
//                "\t\t<memo></memo>\n" +
//                "\t</pensioninfo>\n" +
//                "\t</pensiondata></DataSet>>cderbdverd\n";
//
//        System.out.println("最初的xml："+xml);
//        int indexone = xml.indexOf("<DataSet>")+"<DataSet>".length();
//        int indextwo = xml.indexOf("<pensiondata>");
//        String xml2 = xml.substring(0, indexone);
//        String xml3 = xml.substring(indextwo, xml.length());
//        System.out.println("截取后的xml："+xml2+xml3);
//        byte[] bytes = xml.getBytes("UTF-8");



        //浙江特殊字段的处理
        String str = "  ".toString();
//        String str = "20201120T12110100000000 ";
        boolean specialChar = isSpecialChar(str);
        System.out.println("是否包含特殊字段specialChar ："+specialChar);
    }


    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        String regEx1 = "\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);

        return m.find();
    }

}
