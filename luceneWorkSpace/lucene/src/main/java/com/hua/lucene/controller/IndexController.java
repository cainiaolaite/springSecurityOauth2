package com.hua.lucene.controller;

import com.hua.lucene.entity.Commodity;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.eclipse.jdt.internal.compiler.util.FloatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring boot RestController 是用于放回数据，不能返回页面
 */
@Controller
public class IndexController {
    @Autowired
    private Directory directory;

    @RequestMapping("/")
    public String index(){
      return "/index.jsp";
    }


    /**
     * 查询商品
     * @param sreach
     * @return
     */
    @GetMapping("/query")
    @ResponseBody
    public List<Commodity> query(String sreach) throws Exception {
       Analyzer analyzer=new IKAnalyzer();
       QueryParser queryParser=new QueryParser(Version.LUCENE_47,"description",analyzer);
       Query query = queryParser.parse(sreach);
       List<Document> documentList=sreachDirectory(query);
       return documentListToCommodity(documentList);
    }


  /**
   * 对象转换
   * @param documentList
   * @return
   */
    private List<Commodity> documentListToCommodity(List<Document> documentList){
        List<Commodity> commodityList=new ArrayList<>();
        for(Document document:documentList){
            Commodity commodity=new Commodity();
            commodity.setId(document.get("id"));
            commodity.setName(document.get("name"));
            commodity.setPrice(new Float(document.get("price")));
            commodity.setDescription(document.get("description"));
            commodity.setImagePath(document.get("imagePath"));
            commodityList.add(commodity);
        }
        return commodityList;
    }


  public List<Document> sreachDirectory(Query query) throws IOException, ParseException {
    //1.读取索引库
    IndexReader indexReader= DirectoryReader.open(directory);
    //2.构建查询索引器
    IndexSearcher indexSearcher=new IndexSearcher(indexReader);
    //3.获取查询对象
    TopDocs topDocs=indexSearcher.search(query,100);
    //4.取出具体结果
    ScoreDoc[] scoreDocs=topDocs.scoreDocs;
    List<Document> documents=new ArrayList<>();
    for (ScoreDoc scoreDoc : scoreDocs) {
      Document document = indexSearcher.doc(scoreDoc.doc);
      documents.add(document);
    }
    indexReader.close();
    return documents;
  }


}
