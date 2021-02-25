import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object tweetQuery
{
  def main(args: Array[String]): Unit = {

    System.setProperty("hadoop.home.dir", "C:\\winutils")


    val sparkConf = new SparkConf().setAppName("SparkWordCount").setMaster("local[*]")

    val sc1= new sparkcontext(sparkconf)
    val sc = new SparkContext(sparkConf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)
    val spark = SparkSession.builder().appName("Spark SQL basic example").config("spark.master.config.option", "local[*]").getOrCreate()

    // this is used to implicitly convert an RDD to a DataFrame.
    import spark.implicits._
    //val df= spark.read.json("C:\\Users\\Vishnu Sree Narne\\Desktop/task1.json")
    //df.filter($"_corrupt_record".isNotNull).count()
    val df = sqlContext.read.json("data\\tweets.json")
    df.createOrReplaceTempView("nflDataTable")

    val query1= sqlContext.sql("select user.location,user.followers_count from nflDataTable")
    query1.coalesce(1).write.csv("output/query1")

    val query2=sqlContext.sql("select user.name,user.followers_count from nflDataTable where text LIKE '%nfl%'")
    val hrcount=query2.count()
    query2.coalesce(1).write.csv("output/query2")

    val query3= sqlContext.sql("select user.location,source from nflDataTable")
    query3.coalesce(1).write.csv("output/query3")

    //September tweets
    val query4=sqlContext.sql("select user.name, SUBSTRING(user.created_at,5,3) AS month,SUBSTRING(user.created_at,12,8) AS time," +
       "Count(*) as cnt from nflDataTable where SUBSTRING(user.created_at,5,3) like '%Sep%' group by user")
    query4.show()
    val sepcount=query4.count()
    println("Sep queries %s".format(sepcount))
    query4.coalesce(1).write.csv("output/query4")


     //    5. Query to find out the person with large number of followers about NFL Games
    val query5 =sqlContext.sql("select user.location, max(user.followers_count) AS followers_count from nflDataTable where user.followers_count>100 group by user.location")
    query5.show()
    query5.coalesce(1).write.csv("output/query5")

    //user with more number of favourites count of tweets
    val query6 =sqlContext.sql("select user.location, max(user.favourites_count) AS fav_count from nflDataTable where user.favourites_count>100 group by user.location")
    query6.show()
    query6.coalesce(1).write.csv("output/query6")

    val query7=sqlContext.sql("select user.name,user.statuses_count from nflDataTable where text LIKE '%nfl%'")
    val query7count=query7.count()
    query7.coalesce(1).write.csv("output/query7")

    val query8=sqlContext.sql("select user.name,user.friends_count,text from nflDataTable where where user.friends_count>100 order by user.name")
    val query8count=query8.count()
    query8.coalesce(1).write.csv("output/query8")

    val query9=
    sqlContext.sql("select user.listed_count, user.location from nflDataTable where user.followers_count>10 ")
    query9.coalesce(1).write.csv("output/query9")

    val query10=sqlContext.sql("select user.name, SUBSTRING(user.created_at,5,3) AS month,SUBSTRING(user.created_at,12,8) AS time from nflDataTable where text LIKE '%en%' group by user")
    query10.show()
    query10.coalesce(1).write.csv("output/query10")

    val queryjoin=sqlContext.sql("select user.name,user.followers_count,user.favourites_count,user.listed_count,user.statuses_count,user.friends_count from nflDataTable where text LIKE '%NFL' ")
    queryjoin.show()
    queryjoin.coalesce(1).write.csv("output/query11")

  }
}