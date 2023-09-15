package com.xiechanglei.code.base.common.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CaiPiao {
    public static void main1(String[] args) throws FileNotFoundException {
        int year = 23;
        int no = 106;
        PrintWriter printWriter = new PrintWriter("/home/xie/caipiao.sql");
        while (year > 0) {
            while (no > 0) {
                Connection connect = Jsoup.connect("https://www.zjlottery.com/win1/L/20" + year + "/" + (year * 1000 + no) + ".html");
                try {
                    Document document = connect.get();
                    String date = document.select(".n_01").text().replaceAll("开奖日期：|日| |　", "").replaceAll("[年月]", "-");
                    String money = document.select(".n_02").text().replaceAll("本期全国销售金额：|元|　|,| ", "");
                    String number = document.select(".n_06").text().replace("本期开奖号码：", "").trim();
                    CaiPiaoInfo info = new CaiPiaoInfo();
                    info.setDate(date);
                    info.setMoney(Long.valueOf(money));
                    List<Integer> collect = Arrays.stream(number.split("([ 　])+")).filter(s -> !s.trim().isEmpty()).map(Integer::valueOf).collect(Collectors.toList());
                    info.setNumber(collect);
                    info.setNo(year * 1000 + no);
                    printWriter.println(info.toSQL());
                    System.out.println(info.toSQL());
                    printWriter.flush();
                } catch (Exception ignored) {
                }
                no--;
            }
            year--;
            no = 150;
        }
    }

    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("/home/xie/caipiao.sql");
        for (int i = 0; i < 80; i++) {
            String url = "https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=" + i;
            System.out.println(url);
            try {
                String body = Jsoup.connect(url).ignoreContentType(true).execute().body();
                ObjectMapper objectMapper = new ObjectMapper();
                Map map = objectMapper.readValue(body, Map.class);
                map = (Map) map.get("value");
                List data = (List) map.get("list");
                for (Object f1:data) {
                    Map m1 = (Map) f1;
                    CaiPiaoInfo info = new CaiPiaoInfo();
                    info.setNo(Integer.valueOf(m1.get("lotteryDrawNum").toString()));
                    info.setDate(m1.get("lotteryDrawTime").toString());
                    List<Integer> collect = Arrays.stream(m1.get("lotteryDrawResult").toString().split(" ")).filter(s -> !s.trim().isEmpty()).map(Integer::valueOf).collect(Collectors.toList());
                    info.setNumber(collect);
                    printWriter.println(info.toSQL());
//                    System.out.println(info.toSQL());
                    printWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
