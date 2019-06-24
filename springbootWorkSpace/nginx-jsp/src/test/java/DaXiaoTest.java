import java.text.DecimalFormat;
import java.util.Random;

/**
 * 大小模拟测试
 */
public class DaXiaoTest {



  public static boolean randme(){
     return new Random().nextBoolean();
  }



  public static void main(String[] sta){

      int totalManny=0;
      int benqian=0;

      boolean tou=true;
      int lianxucishu=50;//如果连续5次每中，那么就是回滚 100 200 400 800 1600 3200 6400 12800


      int meiCiTouManny=1;
      int touManny=meiCiTouManny;
      int buZhongDeCishu=0;

      int sucCount=0;
      int failCount=0;
      int totalCount=0;
      int lianXuJiCiMeiZhongCount=0;



      while (totalManny <= 1000000){
          boolean randmeTou=randme();
          totalCount++;
          if(randmeTou == tou){
             totalManny=totalManny+touManny;
             buZhongDeCishu=0;
             touManny=meiCiTouManny;
             sucCount++;
          }else{
             totalManny=totalManny-touManny;
             touManny=touManny * 2;
             buZhongDeCishu++;
             failCount++;
          }

          //连续 lianxucishu 没中
          if(buZhongDeCishu == lianxucishu){
             touManny= meiCiTouManny;
             buZhongDeCishu=0;
             lianXuJiCiMeiZhongCount++;
          }

          if(totalManny < benqian){
              benqian=totalManny;
          }

          DecimalFormat df = new DecimalFormat("0.00");//格式化小数
          String num = df.format((float)failCount/totalCount);//返回的是String类型


        System.out.println("总金额："+totalManny+
              "\t预备款"+(-benqian)+
              "\t总次数："+totalCount+
              "\t成功次数："+sucCount+
              "\t失败次数："+failCount+
              "\t失败占比"+num+
              "\t连续"+lianxucishu+"次失败次数"+lianXuJiCiMeiZhongCount);
      }



  }





}
