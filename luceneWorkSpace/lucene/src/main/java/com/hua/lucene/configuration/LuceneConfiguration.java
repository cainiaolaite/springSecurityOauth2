package com.hua.lucene.configuration;

import com.hua.lucene.entity.Commodity;
import com.hua.lucene.propertie.CommodityProperties;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FloatField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LuceneConfiguration {

  @Autowired
  public CommodityProperties commodityProperties;

  /**
   * 写索引配置
   * @return
   */
  public IndexWriterConfig indexWriterConfig(){
    //为索引添加分词器
    Analyzer analyzer=new IKAnalyzer();
    IndexWriterConfig indexWriterConfig=new IndexWriterConfig(Version.LUCENE_47,analyzer);
    return indexWriterConfig;
  }
  /**
   * document
   * @return
   */
  public List<Document> addDocuments(IndexWriter indexWriter) throws IOException {
    List<Document> documentList=new ArrayList<>();
    for(Commodity commodity:commodityProperties.getCommondityList()){
      Document document=new Document();
      document.add(new TextField("id",commodity.getId(), Field.Store.YES));
      document.add(new TextField("name",commodity.getName(), Field.Store.YES));
      //new FloatPoint("price",commodity.getPrice())
      document.add(new FloatField("price",commodity.getPrice(), Field.Store.YES));
      document.add(new TextField("imagePath",commodity.getImagePath(), Field.Store.YES));
      document.add(new TextField("description",commodity.getDescription(), Field.Store.YES));
      indexWriter.addDocument(document);
    }
    return documentList;
  }


  /**
   * 创建索引库
   * @return
   */
  @Bean
  public Directory directory() throws IOException {
    //1.创建索引库
    Directory directory = FSDirectory.open(new File("C:\\wuhaihua\\workspace\\test"));
    //2.写入索引 索引库要有索引
    IndexWriter indexWriter=new IndexWriter(directory,indexWriterConfig());
    //3.往索引库里添加Document
    addDocuments(indexWriter);
    //4.释放资源
    indexWriter.close();
    return directory;
  }
}
