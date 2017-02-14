package com.jx.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class HttpManager {  
	
	private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String GET = "GET"; // GET
    private static final String POST = "POST";// POST
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    
    
    /**
     * 
     * @description 功能描述: get 请求
     * @return 返回类型:
     * @throws Exception
     */
    public static String get(String urlStr, String params) throws Exception {
        HttpURLConnection conn = null;
        StringBuffer result = new StringBuffer();
        InputStream in = null;
        try{
        	if (isHttps(urlStr)) {
        		conn = initHttps(initParams(urlStr, params), GET);
        	} else {
        		conn = initHttp(initParams(urlStr, params), GET);
        	}
        	// 设置通用的请求属性
        	conn.setRequestProperty("accept", "*/*");
        	conn.setRequestProperty("connection", "Keep-Alive");
        	conn.setRequestProperty("user-agent",
        			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
        	conn.setRequestProperty("content-type","text/json");
        	conn.setDoInput(true); //允许输入流，即允许下载 
        	conn.setDoOutput(false); //允许输出流，即允许上传 
        	
        	if(conn.getResponseCode() != 200){

            }
        	
        	in = conn.getInputStream();
        	BufferedReader read = new BufferedReader(new InputStreamReader(in,
        			DEFAULT_CHARSET));
        	String valueString = null;
        	
        	while ((valueString = read.readLine()) != null) {
        		result.append(valueString);
        	}
        	
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(null != in){
            	in.close();
            	in = null;
            }
            if(null != conn){
                conn.disconnect();
            }
        }
        return result.toString();
    }
    
    public static String post(String urlStr, String params) throws Exception {
    	 HttpURLConnection conn = null;
         StringBuffer result = new StringBuffer();
         InputStream in = null;
         try{
         	if (isHttps(urlStr)) {
         		conn = initHttps(urlStr, POST);
         	} else {
         		conn = initHttp(urlStr, POST);
         	}
         	// 设置通用的请求属性
         	conn.setRequestProperty("accept", "*/*");
         	conn.setRequestProperty("connection", "Keep-Alive");
         	conn.setRequestProperty("user-agent",
         			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
         	conn.setRequestProperty("content-type","text/json");
         	conn.setRequestProperty("content-length",String.valueOf(params.getBytes().length));
         	conn.setDoInput(true); //允许输入流，即允许下载 
        	conn.setDoOutput(true); //允许输出流，即允许上传 
         	OutputStream out = conn.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();

            if(conn.getResponseCode() != 200){

            }
         	in = conn.getInputStream();
         	BufferedReader read = new BufferedReader(new InputStreamReader(in,
         			DEFAULT_CHARSET));
         	String valueString = null;
         	
         	while ((valueString = read.readLine()) != null) {
         		result.append(valueString);
         	}
         	
         } catch (Exception ex) {
             ex.printStackTrace();
         } finally {
             if(null != in){
             	in.close();
             	in = null;
             }
             if(null != conn){
                 conn.disconnect();
             }
         }
         return result.toString();
    }
    
    /**
     * 下载文件
     * 
     * @param urlStr	请求路径
     * @param params	请求参数
     * @param fileSrc	文件存放路径
     * @throws Exception
     */
   public static void download(String urlStr, String params, String fileSrc) throws Exception{
       HttpURLConnection conn = null;
       InputStream in = null;
       try{
		   	if (isHttps(urlStr)) {
		   		conn = initHttps(initParams(urlStr, params), GET);
		   	} else {
		   		conn = initHttp(initParams(urlStr, params), GET);
		   	}
		   	// 设置通用的请求属性
		   	conn.setRequestProperty("accept", "*/*");
		   	conn.setRequestProperty("connection", "Keep-Alive");
		   	conn.setRequestProperty("user-agent",
		   			"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		   	conn.setRequestProperty("content-type","text/json");
		   	conn.setDoInput(true); //允许输入流，即允许下载 
		   	conn.setDoOutput(false); //允许输出流，即允许上传 

           if(conn.getResponseCode() != 200){

           }
           in = conn.getInputStream();
           MapleFileUtil.createFile(in, fileSrc);
       }catch(Exception ex){
           ex.printStackTrace();
       }finally {
           if(null != in){
           	in.close();
           	in = null;
           }
           if(null != conn){
               conn.disconnect();
           }
       }
   }
    
    
    /**
     * 上传文件到微信服务器
     * @param urlStr 上传的目标url
     * @param fileSrc 文件路路径
     * @Param formDataName 表单id
     * @return
     * @throws Exception
     */
    public static String upload(String urlStr,String fileSrc) throws Exception{
    	HttpURLConnection conn = null;
        StringBuffer result = new StringBuffer();
        InputStream in = null;
        OutputStream out = null;
        final String BOUNDARYSTR = ""+System.currentTimeMillis();
        final String BOUNDARY = "--"+BOUNDARYSTR+"\r\n";
        try{
        	if (isHttps(urlStr)) {
        		conn = initHttps(urlStr, POST);
        	} else {
        		conn = initHttp(urlStr, POST);
        	}
            File file = new File(fileSrc);
            if(!file.exists() || !file.isFile()){
                String errorMsg = "文件["+fileSrc+"]不存在。无法上传。";
                throw new Exception(errorMsg);
            }
            conn.setDoInput(true); //允许输入流，即允许下载 
        	conn.setDoOutput(true); //允许输出流，即允许上传 
            conn.setUseCaches(false);
            
            // 设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-type", "multipart/form-data;boundary=" + BOUNDARYSTR);
            
            // 请求正文信息  
            StringBuilder sb = new StringBuilder();
            
            //这块是上传video是必须的参数，你们可以在这里根据文件类型做if/else 判断  
//            sb.append(BOUNDARY);  
//            sb.append("Content-Disposition: form-data;name=\"description\" \r\n\r\n");  
//            sb.append(j.toString()+"\r\n"); 
            
            sb.append(BOUNDARY);
            sb.append("Content-Disposition: form-data;name=\"media\";filename=\""  
                    + file.getName() + "\";filelength=\"" + file.length() + "\" \r\n");  
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            // 获得输出流
            out = new DataOutputStream(conn.getOutputStream());
            // 输出表头
            out.write(head);
            // 文件正文部分
            // 把文件已流文件的方式 推入到url中
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = bis.read(bufferOut,0,1024)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            bis.close();
            byte[] foot = ("\r\n--" + BOUNDARYSTR + "--\r\n").getBytes(DEFAULT_CHARSET);// 定义最后数据分隔线
            out.write(foot);
            out.flush();
            
            if(conn.getResponseCode() != 200){

            }
            in = conn.getInputStream();
         	BufferedReader read = new BufferedReader(new InputStreamReader(in,
         			DEFAULT_CHARSET));
         	String valueString = null;
         	
         	while ((valueString = read.readLine()) != null) {
         		result.append(valueString);
         	}
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            if(null != in){
            	in.close();
            	in = null;
            }
            if(null != conn){
                conn.disconnect();
            }
        }
        return result.toString();
    }
    
    /**
     * 初始化http请求参数
     * 
     * @param urlStr
     * @param method
     * @param headers
     * @return
     * @throws Exception
     */
    private static HttpURLConnection initHttp(String urlStr, String method) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        // 连接超时
        http.setConnectTimeout(DEF_CONN_TIMEOUT);
        // 读取超时 --服务器响应比较慢，增大时间
        http.setReadTimeout(DEF_READ_TIMEOUT);
        //不使用缓存
        http.setUseCaches(false);
        //get or post
        http.setRequestMethod(method);
        return http;
    }

    /**
     * 初始化http请求参数
     * 
     * @param url
     * @param method
     * @return
     * @throws Exception
     */
    private static HttpsURLConnection initHttps(String urlStr, String method) throws Exception {
        TrustManager[] tm = { x509TrustManager };
        SSLContext sslContext = SSLContext.getInstance("TLS","SunJSSE");
        sslContext.init(null, tm, new SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        
        
        URL url = new URL(urlStr);
        HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
        //设置SSLSocketFactory对象
        https.setSSLSocketFactory(ssf);
        // 设置域名校验
        https.setHostnameVerifier(hostnameVerifier);
        // 连接超时
        https.setConnectTimeout(DEF_CONN_TIMEOUT);
        // 读取超时 --服务器响应比较慢，增大时间
        https.setReadTimeout(DEF_READ_TIMEOUT);
        //不使用缓存
        https.setUseCaches(false);
        //get or post
        https.setRequestMethod(method);
        
        return https;
    }





    /**
     *
     * @param urlString
     * @return
     */
//    public Map<String,Object> sendSSLGetDownloadMedia(String urlStr){
//        String fileName = null;
//        byte[] repData = null;
//        InputStream is = null;
//        Map<String,Object> resultInfo = null;
//        HttpsURLConnection connection = null;
//        try {
//
//            URL url = new URL(urlString);
//            /*
//             * use ignore host name verifier
//             */
//            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
//            connection = (HttpsURLConnection) url.openConnection();
//            // Prepare SSL Context
//            TrustManager[] tm = { ignoreCertificationTrustManger };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//            connection.setSSLSocketFactory(ssf);
//
//            /**从以下头部数据解析出文件名
//             * Content-disposition: attachment; filename="MEDIA_ID.jpg"
//             */
//            String contentDisposition = connection.getHeaderField("Content-disposition");
//            if(contentDisposition != null){
//                String[] contentDispositionArray = contentDisposition.split(";");
//                for(String content:contentDispositionArray){
//                    if(content.contains("filename")){
//                        String[] contentArry = content.split("=");
//                        fileName = contentArry[1];
//                        fileName = fileName.replaceAll("\"","");
//                    }
//                }
//            }
//            if(connection.getResponseCode() != 200){
//
//            }
//            is = connection.getInputStream();
//            repData = this.ioTool.getByteArrayFromInputStream(is);
//            resultInfo = new HashMap<String,Object>();
//            resultInfo.put("fileName",fileName);
//            resultInfo.put("data",repData);
//        } catch (Exception ex) {
//            logger.error(ex.getMessage());
//            ex.printStackTrace();
//        } finally {
//            if(null != is){
//                try{
//                    is.close();
//                    is = null;
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//            if(null != connection){
//                connection.disconnect();
//            }
//        }
//        return resultInfo;
//    }

    /**
     *
     * @param urlString
     * @param postData
     * @return
     */
//    public Map<String,Object> sendSSLPostDownloadMedia(String urlString, String postData){
//        String fileName = null;
//        byte[] repData = null;
//        InputStream is = null;
//        Map<String,Object> resultInfo = null;
//        HttpsURLConnection connection = null;
//        try{
//            URL url = new URL(urlString);
//            /*
//             * use ignore host name verifier
//             */
//            HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
//            connection = (HttpsURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("content-type","text/json");
//            connection.setRequestProperty("content-length",String.valueOf(postData.getBytes().length));
//            connection.getOutputStream().write(postData.getBytes("utf-8"));
//            connection.getOutputStream().flush();
//            connection.getOutputStream().close();
//            // Prepare SSL Context
//            TrustManager[] tm = { ignoreCertificationTrustManger };
//            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
//            sslContext.init(null, tm, new java.security.SecureRandom());
//
//            // 从上述SSLContext对象中得到SSLSocketFactory对象
//            SSLSocketFactory ssf = sslContext.getSocketFactory();
//            connection.setSSLSocketFactory(ssf);
//
//            /**从以下头部数据解析出文件名
//             * Content-disposition: attachment; filename="MEDIA_ID.jpg"
//             */
//            String contentDisposition = connection.getHeaderField("Content-disposition");
//            String[] contentDispositionArray = contentDisposition.split(";");
//            for(String content:contentDispositionArray){
//                if(content.contains("filename")){
//                    String[] contentArry = content.split("=");
//                    fileName = contentArry[1];
//                    fileName = fileName.replaceAll("\"","");
//                }
//            }
//            if(connection.getResponseCode() != 200){
//
//            }
//            is = connection.getInputStream();
//            repData = this.ioTool.getByteArrayFromInputStream(is);
//            resultInfo = new HashMap<String,Object>();
//            resultInfo.put("fileName",fileName);
//            resultInfo.put("data",repData);
//        }catch (Exception ex){
//            logger.error(ex.getMessage());
//            ex.printStackTrace();
//        }finally {
//            if(null != is){
//                try{
//                    is.close();
//                    is = null;
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            if(null != connection){
//                connection.disconnect();
//            }
//        }
//        return resultInfo;
//
//    }

    /**
     * HostName
     */
    private static HostnameVerifier hostnameVerifier = new HostnameVerifier() {
        public boolean verify(String s, SSLSession sslsession) {
//            System.out.println("WARNING: Hostname is not matched for cert.");
            return true;
        }
    };

    /**
     * Certification
     */
    private static X509TrustManager x509TrustManager = new X509TrustManager(){
        private X509Certificate[] certificates;
        public void checkClientTrusted(X509Certificate certificates[],
                                       String authType) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = certificates;
            }
        }
        public void checkServerTrusted(X509Certificate[] ax509certificate,
                                       String s) throws CertificateException {
            if (this.certificates == null) {
                this.certificates = ax509certificate;
            }
        }
        public X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    };
    
    /**
      * 检测是否https
      * 
      * @param url
      */
     private static boolean isHttps(String url) {
        return url.startsWith("https");
    }
    
    /**
     * 判断某个请求是否是异步的
     * @param request
     * @return
     */
    public boolean isAsynchronousRequest(HttpServletRequest request){

        //Jquery的ajax请求默认会加上这个头部
        String jQueryAjaxHeader = request.getHeader("x-requested-with");
        //原生js使用ajax请加上一个头部参数请求头部:XMLHttpRequest.setRequestHeader("RequestType","AJAX");
        String customAjaxHeader = request.getHeader("RequestType");
        if(jQueryAjaxHeader.equals("XMLHttpRequest") || customAjaxHeader.equals("AJAX") ){
            return  true;
        }
        return false;
    }
    
    /**
     * 功能描述: 构造请求参数
     * 
     * @return 返回类型:
     * @throws Exception
     */
    public static String initParams(String url, String params)
            throws Exception {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(params);
        return sb.toString();
    }

    /**
     * map构造url
     * 
     * @return 返回类型:
     * @throws Exception
     */
    public static String map2Url(Map<String, String> params)
            throws Exception {
        if (null == params || params.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Entry<String, String> entry : params.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (!StringUtils.isEmpty(value)) {
                url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
            }
        }
        return url.toString();
    }
    
    
}
