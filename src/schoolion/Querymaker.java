/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author abosalaah
 */
public class Querymaker 
{
    DBman dbsuperman;
    public Querymaker() throws SQLException
    {
        dbsuperman=new DBman();
    }
    
    public int insertguardian(String gfname,String glname,String gAddress,String phone,String mail,String wphone,String job)
    {
        String apos="'"; String comma=",";
        if(gfname.isEmpty())gfname="null";
        else gfname=apos+gfname+apos;
        if(glname.isEmpty())glname="null";
        else glname=apos+glname+apos;
        if(gAddress.isEmpty())gAddress="null";
        else gAddress=apos+gAddress+apos;
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
        if(wphone.isEmpty())wphone="null";
        else wphone=apos+wphone+apos;
        if(job.isEmpty())job="null";
        else job=apos+job+apos;
        String query="insert into guardian (fname,lname,address,phone,mail,workphone,jop) values("+gfname+comma+glname+comma+gAddress+comma+phone+
                comma+mail+comma+wphone+comma+job+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int insertparent(String fname,String lname,String job,String mothername,String motherjob)
    {
        String apos="'"; String comma=",";
        if(fname.isEmpty())fname="null";
        else fname=apos+fname+apos;
        if(lname.isEmpty())lname="null";
        else lname=apos+lname+apos;
        if(mothername.isEmpty())mothername="null";
        else mothername=apos+mothername+apos;
        if(motherjob.isEmpty())motherjob="null";
        else motherjob=apos+motherjob+apos;
        if(job.isEmpty())job="null";
        else job=apos+job+apos;
        String query="insert into parent values("+fname+comma+lname+comma+job+comma+mothername+comma+motherjob+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public int insertstudent(String fname,String mname,String lname,String sex,String day,String month,String year,String address,String phone,String mail,String bnumber,String stage,String cyear,String number,String gid)
    {
        String comma=","; String apos="'";
        if(fname.isEmpty())fname="null";
        else fname=apos+fname+apos;
        if(mname.isEmpty())mname="null";
        else mname=apos+mname+apos;
        if(lname.isEmpty())lname="null";
        else lname=apos+lname+apos;
        if(sex.isEmpty())sex="null";
        else sex=apos+sex+apos;
        String bdate=apos+year+"-"+month+"-"+day+apos;
        if(address.isEmpty())address="null";
        else address=apos+address+apos;
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
        if(bnumber.isEmpty())bnumber="null";
        else bnumber=apos+bnumber+apos;
        String cid=cyear+stage+number;
        if(gid.isEmpty())gid="null";
        else gid=apos+gid+apos;
        String query="insert into student (fname,sname,lname,sex,bdate,address,phone,mail,cid,bnumber,gid) values("+fname+comma+mname+comma+lname+comma+
                sex+comma+bdate+comma+address+comma+phone+comma+mail+comma+
                cid+comma+bnumber+comma+gid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public boolean checkifclassfull(String cid) throws SQLException
    {
        String query="select *from class where cid="+cid+";";
        ResultSet myres=dbsuperman.select(query);
         if (!myres.isBeforeFirst())return false;
        if(myres!=null)
        {
            while(myres.next())
            {
               int size=Integer.parseInt(myres.getString("csize")); 
               int capacity=Integer.parseInt(myres.getString("capacity")); 
               if(size<capacity)return true;
               else return false;
            }
        }
       
        return false;
    }
    public int insertevent(String name,String supervisor,String startday,String startmonth,String startyear,String endday,
            String endmonth,String endyear,String fees,String follower,String efor,String capacity)
    {
        String comma=",";
        if(name.isEmpty())name="null";
        else name="'"+name+"'";
          if(supervisor.isEmpty())supervisor="null";
        else supervisor="'"+supervisor+"'";
           
          if(fees.isEmpty())follower="default";
          
           efor="'"+efor+"'";
          if(capacity.isEmpty())capacity="null";
              
        
        
        
        
        
        
        String query="insert into event (name,supervisor,startdate,enddate,fees,follower,participation,capacity) values("+name+comma+supervisor+comma+"'"+startyear+"-"+startmonth+"-"+startday+"'"+comma+
                "'"+endyear+"-"+endmonth+"-"+endday+"'"+comma+fees+comma+follower+comma+efor+comma+capacity+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    
    public ResultSet searchforevent(String eid,String name,String supervisor,String startday,String startmonth,String startyear,String endday,
            String endmonth,String endyear,String fees,String follower,String efor,String capacity)
    {
        //bool to find if i enter in any if condition
    boolean foundwhere=false;
    System.out.println(follower);
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from event where ";
    String apos="'";
    if(!name.isEmpty())
    {
        query+="name="+apos+name+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!supervisor.isEmpty())
    {
       if(firstwhere>0) {query+=" and ";}
       query+="supervisor="+apos+supervisor+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!eid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="eid="+apos+eid+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!startday.equals("00")&&!startmonth.equals("00")&!startyear.equals("0000"))
    {
        if(firstwhere>0)query+=" and ";
        query+="startdate="+apos+startyear+"-"+startmonth+"-"+startday+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!endday.equals("00")&&!endmonth.equals("00")&!endyear.equals("0000"))
    {
       if(firstwhere>0) query+=" and ";
        query+="enddate="+apos+endyear+"-"+endmonth+"-"+endday+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!fees.isEmpty()){
        if(firstwhere>0)query+=" and ";
         query+="fees="+fees; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!follower.equals("non")){
        if(firstwhere>0)query+=" and "; 
         query+="follower="+follower; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!efor.equals("non")){
        if(firstwhere>0)query+=" and ";
        query+="participation="+apos+efor+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!capacity.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="capacity="+capacity; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(foundwhere);
    if(!foundwhere)return null;
    else return dbsuperman.select(query);
    }
    
    
    public ResultSet selectalldata(String data)
    {
        String query="select *from "+data+";";
        return dbsuperman.select(query);
        
    }
    
    public boolean iseventexistforstudent(String eid) throws SQLException
    {
        String query="select *from event where (participation='student' or participation ='both') and eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
        System.out.println("myres "+myres);
        if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
                System.out.println("ana hnaa");
               int size=Integer.parseInt(myres.getString("capacity"));
               System.out.println(size);
               if(size>0)return true;
            }
            return false;
        
        }
    }
    public boolean checkiffollowerin(String eid,String sid) throws SQLException
    {
        String query="select *from event where  eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
            while(myres.next())
            {
                System.out.println("ana gwa follower in");
               int follower=Integer.parseInt(myres.getString("follower"));
               System.out.println(follower);
               if(follower==0)return false;
               
            }
            System.out.println("ana hnaaaaaaaaaaaaaaaaaaaa");
            String query2="select *from sje where sid="+sid+" and eid="+eid+";";
            System.out.println(query2);
            ResultSet myres2=dbsuperman.select(query2);
             if (!myres2.first())
             { System.out.println("wslt hnaaaaaaaaaaa");
                 return false;}
             else return true;
                 
    }
    public boolean checkfor3followers(String sid,String eid) throws SQLException
    {
        String query="select *from follower where sid="+sid+" and "+"eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
          if (!myres.isBeforeFirst())return true;
        else 
        {
            int cnt=0;
            while(myres.next())
            {
               ++cnt;
            }
            if(cnt<3)return true;
            else return false;
        
        }
        
    }
    public int insertfollower(String followername,String sid,String eid)
    {
        String query="insert into follower values("+"'"+followername+"'"+","+sid+","+eid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int insertstudentinevent(String sid,String eid)
    {
        System.out.println("ana get hnaa");
        String query="insert into sje values("+eid+","+sid+");";
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int updateeventsize(String eid,String condition,String value)
    {
        String query="";
       if(condition=="dec") 
       {
           query="update event set capacity=capacity-"+value+" where eid="+eid+";";
       }  
       else if(condition=="inc")
       {
            query="update event set capacity=capacity+"+value+" where eid="+eid+";";
       }
       int myres=dbsuperman.eid(query);
        return myres;
    }
    public boolean iseventexistforguardian(String eid) throws SQLException
    {
         String query="select *from event where (participation='parent' or participation ='both')  and eid="+eid+";";
        ResultSet myres=dbsuperman.select(query);
         if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
               int size=Integer.parseInt(myres.getString("capacity"));
               if(size>0)return true;
            }
            return false;
        
        }
    }
    public int insertguardianinevent(String gid,String eid)
    {
        String query="insert into gje values("+eid+","+gid+");";
         int myres=dbsuperman.eid(query);
        return myres;
    }
    public ResultSet getrowselected(String table,String id,String typeid)
    {
        String query="select *from "+table+" where "+id+"="+"'"+typeid+"'"+";";
        System.out.println(query);
        return dbsuperman.select(query);
    }
    public int updaterowevent(String name,String supervisor,String sday,String smonth,String syear,String eday,String emonth,String eyear,
            String fees,String follower,String efor,String capacity,String preveid)
    {
        String apos="'"; String comma=","; String dash="-";
        String sdate=""; String edate="";
        if(name.isEmpty())name="null";
        else name=apos+name+apos;
        if(supervisor.isEmpty())supervisor="null";
        else supervisor=apos+supervisor+apos;
        if(sday.equals("00")||smonth.equals("00")||syear.equals("0000"))sdate="null";
        else sdate=apos+syear+"-"+smonth+"-"+sday+apos;
        if(eday.equals("00")||emonth.equals("00")||eyear.equals("0000"))edate="null";
        else edate=apos+eyear+"-"+emonth+"-"+eday+apos;
        if(follower.equals("non"))follower="null";
  
        if(efor.equals("non"))efor="null";
        else efor=apos+efor+apos;
        if(capacity.isEmpty())capacity="null";
        if(fees.isEmpty())fees="default";
       
         String query="update event set name="+name+","+"supervisor="+supervisor+","+
            "fees="+fees+comma+"startdate="+sdate+comma+
                    "enddate="+edate+comma+"follower="+follower+comma+
                    "participation="+efor+comma+" where "+
                    "eid="+preveid+";";
        
        return dbsuperman.eid(query);
        
    }
    public int updaterowsevent(String name,String supervisor,String sday,String smonth,String syear,String eday,String emonth,String eyear,
            String fees,String follower,String efor,String capacity,String idintable)
    {
         
                   
                    String comma=","; String apos="'"; String dash="-";
                    String query="update event set ";
                    boolean found=false; int firstset=0; 
                    
     if(!name.isEmpty())
    {
        query+="name="+apos+name+apos; found=true;
        ++firstset;
    }
    if(!supervisor.isEmpty())
    {
       if(firstset>0) {query+=",";}
        query+="supervisor="+apos+supervisor+apos; 
       found=true;
       ++firstset;
    }
   
    if(!sday.equals("00")&&!smonth.equals("00")&!syear.equals("0000"))
    {
        if(firstset>0)query+=",";
        query+="startdate="+apos+syear+"-"+smonth+"-"+sday+apos;
        found=true;
        ++firstset;
    }
    if(!eday.equals("00")&&!emonth.equals("00")&!eyear.equals("0000"))
    {
        if(firstset>0)query+=",";
        query+="enddate="+apos+eyear+"-"+emonth+"-"+eday+apos;
        found=true;
        ++firstset;
    }
    if(!fees.isEmpty()){
        if(firstset>0)query+=",";
         query+="fees="+fees; 
        found=true;
        ++firstset;
    }
    if(!follower.equals("non")){
        if(firstset>0)query+=","; 
         query+="follower="+follower; 
        found=true;
        ++firstset;    
    }
    if(!efor.equals("non")){
        if(firstset>0)query+=",";
         query+="participation="+apos+efor+apos; 
        found=true;
        ++firstset;
    }
    query+=" where eid="+apos+idintable+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
    }
    public int inertclass(String year,String stage,String num,String sf,String size,String capacity,String level,String tid,String cid,String sid)
    {
        String comma=",";
        System.out.println(cid);
        String query="insert into class values("+year+comma+stage+comma+num+comma+sf+comma+size+comma+capacity+comma+level+comma+tid+comma+cid+comma+sid+");";
        System.out.println(query);
        int myres=dbsuperman.eid(query);
        return myres;
    }
    public int deleteselectedrow(String table,String id,String typeid)
    {
        String query="delete from "+table+" where "+id+"="+"'"+typeid+"'"+";";
        return dbsuperman.eid(query);
    }
    
    public ResultSet searchforclass(String year,String stage,String number,String sf,String size,String capacity,String level
           ,String cid)
    {
        //bool to find if i enter in any if condition
    boolean foundwhere=false;
    
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from class where ";
    String apos="'";
    if(!year.equals("n"))
    {
        query+="year="+apos+year+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!stage.equals("n"))
    {
       if(firstwhere>0) {query+=" and ";}
       query+="stage="+apos+stage+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!number.equals("n")){
        if(firstwhere>0)query+=" and ";
        query+="number="+apos+number+apos;
        foundwhere=true;
        ++firstwhere;
    }
    
    if(!sf.equals("n")){
        if(firstwhere>0)query+=" and ";
         query+="sciencefield="+apos+sf+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!size.isEmpty()){
        if(firstwhere>0)query+=" and "; 
         query+="csize="+size; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!capacity.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="capacity="+capacity; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!level.equals("n")){
        if(firstwhere>0)query+=" and ";
        query+="level="+apos+level+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    
    if(!cid.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="cid="+apos+cid+apos; 
        foundwhere=true;
        ++firstwhere;
    }
   
    query+=";";
    System.out.println(query);
    System.out.println(foundwhere);
    if(!foundwhere)return null;
    else return dbsuperman.select(query);
    }
      public int updateclasssize(String cid)
    {
        String query="update class set csize=csize+1 where cid="+cid+";";
        int myres=dbsuperman.eid(query);
        return myres;
    }
      
    public int updaterowsclass(String year,String stage,String number,String sf,String size,String capacity,String level
            ,String cid,String prevcid)
    {
                   String comma=","; String apos="'"; String dash="-";
                    String query="update class set ";
                    boolean found=false; int firstset=0; 
                    
     if(!year.equals("n"))
    {
        query+="year="+apos+year+apos; found=true;
        ++firstset;
    }
    if(!stage.equals("n"))
    {
       if(firstset>0) {query+=",";}
        query+="stage="+apos+stage+apos; 
       found=true;
       ++firstset;
    }
    if(!number.equals("n")){
        if(firstset>0)query+=",";
        query+="number="+apos+number+apos;
        found=true;
        ++firstset;
    }
    
    if(!sf.equals("n")){
        if(firstset>0)query+=",";
         query+="sciencefield="+apos+sf+apos; 
        found=true;
        ++firstset;
    }
    if(!size.isEmpty()){
        if(firstset>0)query+=","; 
         query+="csize="+size; 
        found=true;
        ++firstset;    
    }
    if(!capacity.isEmpty()){
        if(firstset>0)query+=",";
         query+="capacity="+capacity; 
        found=true;
        ++firstset;
    }
    if(!level.equals("n")){
        if(firstset>0)query+=",";
         query+="level="+apos+level+apos; 
        found=true;
        ++firstset;
    }
    
    if(!cid.isEmpty()){
        if(firstset>0)query+=",";
         query+="cid="+apos+cid+apos; 
        found=true;
        ++firstset;
    }
    
    query+=" where cid="+apos+prevcid+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
    }
    
    public int updaterowclass(String year,String stage,String number,String sf,String size,String capacity,String level,
            String cid,String prevcid)
    {
        
         String comma=","; String apos="'"; 
        
         String query="update class set year="+apos+year+apos+","+"stage="+apos+stage+apos+","+
            "csize="+size+comma+"capacity="+capacity+comma+
                    "level="+apos+level+apos+comma+"number="+apos+number+apos+comma+"sciencefield="+apos+sf+apos+comma+
                 "cid="+apos+cid+apos+
                 
                 " where "+
                    "cid="+prevcid+";";
        System.out.println(query);
        return dbsuperman.eid(query);
        
                   
    }
    
    
    
    public int insertbus(String driver,String supervisor,String capacity,String size,String des)
    {
        String comma=",";
        String query="insert into bus (driver,supervisor,capacity,bsize,destination) values ("+driver+comma+supervisor+comma+capacity+comma+size+comma+des+");";
        System.out.println(query);
        return dbsuperman.eid(query);
    }
    public ResultSet searchforbus(String num,String driver,String supervisor,String capacity,String size,String des)
    {
        String query="select *from bus where ";
        boolean found=false; int first=0;
        if(!num.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bnumber= "+"'"+num+"'";
            ++first;
            found=true;
        }
        if(!driver.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="driver= "+"'"+driver+"'";
            ++first;
             found=true;
        }
        if(!supervisor.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="supervisor= "+"'"+supervisor+"'";
            ++first;
             found=true;
        }
        if(!capacity.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="capacity= "+capacity;
            ++first;
             found=true;
        }
        if(!size.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bsize= "+size;
            ++first;
             found=true;
        }
        if(!des.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="destination="+"'"+des+"'";
            ++first;
            found=true;
        }
        query+=";";
        System.out.println(found);
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        else return null;
    }
    public int updaterowbus(String driver,String supervisor,String capacity,String size,String des,String prevbnumber)
    {
        String apos="'"; String comma=",";
        if(driver.isEmpty())driver="null";
        else driver=apos+driver+apos;
        if(supervisor.isEmpty())supervisor="null";
        else supervisor=apos+supervisor+apos;
        if(capacity.isEmpty())capacity="null";
        else capacity=apos+capacity+apos;
        if(size.isEmpty())size="null";
        else size=apos+size+apos;
        if(des.isEmpty())des="null";
        else des=apos+des+apos;
        String query="update bus set driver="+driver+comma+"supervisor="+supervisor+comma+
                "capacity="+capacity+comma+"bsize="+size+ " where bnumber="+apos+prevbnumber+apos+";";
        return dbsuperman.eid(query);
    }
    
    public int updaterowsbus(String driver,String supervisor,String capacity,String size,String des,String prevbnumber)
    {
        String query="update bus set ";
        boolean found=false; int first=0;
        if(!driver.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="driver="+"'"+driver+"'";
            ++first;
             found=true;
        }
        if(!supervisor.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="supervisor="+"'"+supervisor+"'";
            ++first;
             found=true;
        }
        if(!capacity.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="capacity="+capacity;
            ++first;
             found=true;
        }
        if(!size.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="bsize="+size;
            ++first;
             found=true;
        }
        if(!des.isEmpty())
        {
            if(first>0)query+=" , ";
            query+="destination="+"'"+des+"'";
            ++first;
            found=true;
        }
        query+=" where bnumber="+"'"+prevbnumber+"'"+";";
       
        if(found)
        {
            return dbsuperman.eid(query);
        }
        else return 0;
    }
       public boolean checkforbussize(String bnumber) throws SQLException
    {
        String query="select bsize,capacity from bus where bnumber="+bnumber+";";
        ResultSet myres=dbsuperman.select(query);
        if(myres!=null)
        {
             if (!myres.isBeforeFirst())return false;
        else 
        {
            while(myres.next())
            {
               int size=(myres.getInt("bsize"));
               int cap=(myres.getInt("capacity"));
               if(size<cap)return true;
               else return false;
            }
           
        
        }
             
        }
        
        return false;
    }
    public boolean ifstudentorteacherinbus(String table,String idtype,String id,String bnum) throws SQLException
    {
        String query="select bnumber from "+table+" where "+idtype+"="+id+";";
       
        ResultSet myres=dbsuperman.select(query);
        if(myres!=null)
        {
             if (!myres.isBeforeFirst())return false; //lw mfech result yb2a rg3 false
        else 
        {
            while(myres.next())
            {
               String bnumber=myres.getString("bnumber");
               if(bnumber=="null")//yb2a hwa msh fe bus aslun
                return true;
               else 
               {
                  
                   if(bnum.equals(bnumber))return false; //hna yb2a hwa 3awz yshtrk fe nfs el bus w da mynf3ch
                   int myres2=dbsuperman.eid("update bus set bsize = bsize-1 where bnumber="+bnumber+";");//hna lw hwa kan fe bus w hyn2l mno f bn2s size el bus da wa7ed
                   return true;
                   
               }
            }
           
        
        }
             
        }
        
        return false;
        
    }
    public int studentorteacherparicipateinbus(String table,String idtype,String id,String bnumber)
    {
        String query="update "+table+" set bnumber="+bnumber+" where "+idtype+"="+id+";";
        int myres=dbsuperman.eid(query);
        int myres2=dbsuperman.eid("update bus set bsize = bsize+1 where bnumber="+bnumber+";");
        return myres;
    }
    public int insertteacher(String fname,String mname,String lname,String address,String phone,String mail,String pos,String bnumber)
    {
        String query="insert into teacher (fname,sname,lname,address,phone,mail,position,bnumber) values( ";
        String comma=","; String apos="'";
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
        if(pos.isEmpty())pos="'Newbi'";
        else pos=apos+pos+apos;
        if(bnumber.isEmpty())bnumber="null";
        else bnumber=apos+bnumber+apos;
        if(fname.isEmpty())fname="null";
        else fname=apos+fname+apos;
        if(mname.isEmpty())mname="null";
        else mname=apos+mname+apos;
        if(lname.isEmpty())lname="null";
        else lname=apos+lname+apos;
        if(address.isEmpty())address="null";
        else address=apos+address+apos;
        query+=fname+comma+mname+comma+lname+comma+address+comma+
                phone+comma+mail+comma+pos+comma+bnumber;
        query+=");";
        System.out.println(query);
        return dbsuperman.eid(query);
    }
    
    public ResultSet findparticipantinevent(String eid,String pid,String type,String table)
    {
        String query="select *from "+table;
        int first=0;
        if(!eid.isEmpty())
        {
            if(first==0)query+=" where ";
            else query+=" and ";
            query+="eid="+eid;
            ++first;
            
        }
        if(!pid.isEmpty())
        {
            if(first==0)query+=" where ";
            else query+=" and ";
            query+=type+"="+pid;
            ++first;
        }
        query+=";";
        System.out.println(query);
        return dbsuperman.select(query);
        
    }
    public ResultSet getfollowers(String sid,String eid)
    {
        String query=null;
        if(!eid.equals("0")&&!sid.equals("0"))
        {
            query="select follower.name as followername, student.fname,student.sname ,student.lname ,event.name as eventname from follower,student,event where follower.sid=student.sid and follower.eid=event.eid and follower.sid="+sid+" and event.eid="+eid+";"; 
        }
       else if(!sid.equals("0"))
        {
            query="select follower.name as followername, student.fname,student.sname ,student.lname ,event.name as eventname from follower,student,event where follower.sid=student.sid and follower.eid=event.eid and student.sid="+sid+";"; 
        }
        if(!eid.equals("0"))
        {
             query="select follower.name as followername, student.fname,student.sname ,student.lname ,event.name as eventname from follower,student,event where follower.sid=student.sid and follower.eid=event.eid and event.eid="+eid+";"; 
        }
        System.out.println(query);
        return dbsuperman.select(query);
    }
    public int insertteacherinbus(String tid,String bnumber)
    {
        String query="update teacher set bnumber="+bnumber+" where tid="+tid+";";
        return dbsuperman.eid(query);
    }
    public int removeallfollowersfromevent(String sid,String eid) // de btms7 kol el followers l taleb mo3yn ana shelto mn el event
    {
        String query="delete from follower where sid="+sid+" and eid="+eid+";";
        return dbsuperman.eid(query);
    }
    public int removestudnetfromevent(String sid,String eid)
    {
        String query="delete from sje where sid="+sid+" and eid="+eid+";";
        return dbsuperman.eid(query);
    }
    public int removefollowerfromevent(String followername,String sid,String eid)//de btsm7 follower mo3yn bs
    {
        String query="delete from follower where name="+"'"+followername+"'"+" and sid="+sid+" and eid="+eid+";";
        return dbsuperman.eid(query);
    }
    public int removeguardianfromevent(String gid,String eid)
    {
        String query="delete from gje where gid="+gid+" and eid="+eid+";";
        return dbsuperman.eid(query);
    }
    public ResultSet searchforteacher(String fname,String mname,String lname,String tid,String address,String phone,String mail ,String position,String bnumber)
    {
            //bool to find if i enter in any if condition
    boolean foundwhere=false;
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from teacher where ";
    String apos="'";
    if(!fname.isEmpty())
    {
        if(firstwhere>0) {query+=" and ";}
        query+="fname="+apos+fname+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!mname.isEmpty())
    {
       if(firstwhere>0) {query+=" and ";}
       query+="sname="+apos+mname+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!lname.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="lname="+apos+lname+apos;
        foundwhere=true;
        ++firstwhere;
    }
    if(!tid.isEmpty()){
        if(firstwhere>0)query+=" and ";
         query+="tid="+apos+tid+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!address.isEmpty()){
        if(firstwhere>0)query+=" and "; 
         query+="address="+apos+address+apos; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!phone.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="phone="+apos+phone+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!mail.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="mail="+apos+mail+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!position.equals("non")){
        if(firstwhere>0)query+=" and ";
        query+="position="+apos+position+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!bnumber.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="bnumber="+apos+bnumber+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(query);
    if(!foundwhere)return null;
    else return dbsuperman.select(query); 
    }
    public int updaterowteacher(String fname,String mname,String lname,String address,String phone,String mail ,String position,String bnumber,String previd)
    {
        String comma=","; String apos="'";
        if(fname.isEmpty())fname="null";
        else fname=apos+fname+apos;
        if(mname.isEmpty())mname="null";
        else mname=apos+mname+apos;
        if(lname.isEmpty())lname="null";
        else lname=apos+lname+apos;
        if(address.isEmpty())address="null";
        else address=apos+address+apos;
       
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
        if(position.isEmpty())position="newbi";
        if(bnumber.isEmpty())bnumber="null";
        String query="update teacher set fname="+fname+comma+"sname="+mname+comma+"lname="+lname+comma+
                "address="+address+comma+"phone="+phone+comma+"mail="+mail+comma+"position="+position+comma+
                "bnumber="+bnumber+" where tid="+previd+";";
        return dbsuperman.eid(query);
    }
    public int updaterowsteacher(String fname,String mname,String lname,String address,String phone,String mail ,String position,String bnumber,String previd)
    {
        
         String comma=","; String apos="'"; 
                    String query="update teacher set ";
                    boolean found=false; int firstset=0; 
                    
     if(!fname.isEmpty())
    {
        if(firstset>0)query+=",";
        query+="fname="+apos+fname+apos; found=true;
        ++firstset;
    }
    if(!mname.isEmpty())
    {
       if(firstset>0) {query+=",";}
        query+="sname="+apos+mname+apos; 
       found=true;
       ++firstset;
    }
    if(!lname.isEmpty()){
        if(firstset>0)query+=",";
        query+="lname="+apos+lname+apos;
        found=true;
        ++firstset;
    }
    
    if(!address.isEmpty()){
        if(firstset>0)query+=","; 
         query+="address="+apos+address+apos; 
        found=true;
        ++firstset;    
    }
    if(!phone.isEmpty()){
        if(firstset>0)query+=",";
         query+="phone="+apos+phone+apos; 
        found=true;
        ++firstset;
    }
    if(!mail.isEmpty()){
        if(firstset>0)query+=",";
         query+="mail="+apos+mail+apos; 
        found=true;
        ++firstset;
    }
    if(!position.equals("non")){
        if(firstset>0)query+=",";
         query+="position="+apos+position+apos; 
        found=true;
        ++firstset;
    }
    if(!bnumber.isEmpty()){
        if(firstset>0)query+=",";
         query+="bnumber="+bnumber; 
        found=true;
        ++firstset;
    }
   
    query+=" where tid="+previd;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
        
        
    }
    public String getlastinsertedid(String table,String id) throws SQLException
    {
        String temp = null;
        ResultSet myres=dbsuperman.select("SELECT "+id+" FROM "+table+" ORDER BY "+id+" DESC LIMIT 1;");
         while(myres.next())
        {
            temp= myres.getString(id);
        }
        return temp;
        
    }
    public ResultSet getstudentdataforcombobox()
    {
        return dbsuperman.select("select fname,sname,lname,sid from student;");
    }
    public ResultSet getteacherdataforcombobox()
    {
        return dbsuperman.select("select fname,sname,lname,tid from teacher;");
    }
    public ResultSet geteventdataforcombobox()
    {
        return dbsuperman.select("select name,startdate,eid from event where capacity>0;");
    }
    public ResultSet selectstudentandteacherbyname(String table,String fname,String mname,String lname)
    {
        String query="select *from "+table+" where ";
        boolean found=false; int first=0;
        if(!fname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="fname like '%"+fname+"%'";
            ++first;
            found=true;
        }
        if(!mname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="sname like '%"+mname+"%'";
            ++first;
            found=true;
        }
        if(!lname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="lname like '%"+lname+"%'";
            ++first;
            found=true;
        }
        query+=";";
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        return null;
    }
    public ResultSet selecteventbyname(String name)
    {
      
       
           return dbsuperman.select("select *from event where name like '%"+name+"%'"+";");
       
       
       
       
    }
    public ResultSet getguardiandataforcombobox()
    {
        return dbsuperman.select("select fname,lname, gid from guardian;");
    }
    public ResultSet selectguardianbyname(String fname,String lname)
    {
        String query="select *from guardian where ";
        boolean found=false; int first=0;
        if(!fname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="fname like '%"+fname+"%'";
            ++first;
            found=true;
            
        }
        if(!lname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="lname like '%"+lname+"%'";
            ++first;
            found=true;
            
        }
        query+=";";
        if(found)
        {
            return dbsuperman.select(query);
        }
        return null;
    }
    public ResultSet getallnumberoffollowers(String sid,String eid)
    {
        return dbsuperman.select("select count(*) as num from follower  where sid='"+sid+"' and eid='"+eid+"' ;");
        
    }
    public ResultSet getbusdataforcombobox()
    {
        return dbsuperman.select("select destination,bnumber from bus where (bsize<capacity);");
    }
    public int updatebussize(String bnumber,String condition)
    {
        String query="";
        if(condition=="inc")
        {query="update bus set bsize=bsize+1 where bnumber="+bnumber+";";}
        else if(condition=="dec")
        {
            query="update bus set bsize=bsize-1 where bnumber="+bnumber+";";
        }
        return dbsuperman.eid(query);
    }
    public boolean checkwhenremovestudentorteacherinbus(String table,String idtype,String id) throws SQLException
    {
        ResultSet myres=dbsuperman.select("select bnumber from "+table+" where "+idtype+"="+id+";");
        if(myres!=null)
        {
            while(myres.next())
            {
               
              if((myres.getString("bnumber"))==null)
              {
                 
                  return false;
              }
            else 
              {
                  
                  return true;
              }
            }
        }
        return false;
        
    }
    public int removestudentorteacherformbus(String table,String idtype,String id)
    {
        return dbsuperman.eid("update "+table+" set bnumber=null where "+idtype+"="+id+";");
    }
    public ResultSet getsubjectdataforcombobox()
    {
        return dbsuperman.select("select name,stage,year,code from subject");
    }
    public int insertsubject(String name,String stage,String year,String fullmark,String succesmark,String sf)
    {
        String query="insert into subject (name,stage,year,fullmark,successmark,sciencefield) values( ";
        String comma=","; String apos="'";
        if(name.isEmpty())name="null";
        else name=apos+name+apos;
        if(stage.isEmpty())stage="null";
        else stage=apos+stage+apos;
        if(year.isEmpty())year="'Newbi'";
        else year=apos+year+apos;
        if(fullmark.isEmpty())fullmark="null";
      
        if(succesmark.isEmpty())succesmark="null";
       
        if(sf.isEmpty())sf="null";
        else sf=apos+sf+apos;
       
        query+=name+comma+stage+comma+year+comma+fullmark+comma+
                succesmark+comma+sf;
        query+=");";
        System.out.println(query);
        return dbsuperman.eid(query);
    }
    public int addteachertosubject(String tid,String code)
    {
        return dbsuperman.eid("insert into teach (tid,code) values("+tid+","+code+");");
    }
    public ResultSet getteacherandsubjectdataforaddteachertocalss(String tid,String code)
    {
        String query="select fname,sname,lname,name,stage,year from teacher,subject where teacher.tid="+tid+" and subject.code="+code+";";
        return dbsuperman.select(query);
    }
    public int removeteacherfromteachsubject(String tid,String code)
    {
        return dbsuperman.eid("delete from teach where tid="+tid+" and code="+code+";");
    }
    public int makeandunmaketeacherhighest(String tid,String code)
    {
        return dbsuperman.eid("update subject set tid="+tid+" where code="+code+";");
    }
    public boolean ifteacherishigh(String tid,String code) throws SQLException
    {
        ResultSet myres=dbsuperman.select("select tid form subject where code="+code+";");
        if(myres!=null)
        {
            while(myres.next())
            {
                if((myres.getString("tid"))==tid)return true;
                else return false;
            }
        }
           return false;
        
    }
    public ResultSet getteacherandsubjectdataforcombobox()
    {
        return dbsuperman.select("select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year,teach.teachid from teacher,subject,teach where teacher.tid=teach.tid and subject.code=teach.code;");
        
    }
    public ResultSet getclassdataforcombobox()
    {
        return dbsuperman.select("select cid,sciencefield,level from class;");
    }
    public int insertorremoveclassinteach(String tid,String code,String cid)
    {
        return dbsuperman.eid("insert into  teach (tid,code,cid) values  ("+tid+","+code+","+cid+");");
    }
    public ResultSet getstudentparticipateineventdata1(String sid,String eid)
    {
        return dbsuperman.select("select student.fname,student.sname,student.lname,student.sid,event.name,event.startdate from event,student,sje where student.sid=sje.sid and event.eid=sje.eid and sje.sid="+sid+" and sje.eid="+eid+";"); //hna 3mltha kda l2ny l2et eno hygbly kol el sfof ely fe sje lw ana 7ddtlo id mo3yn bs fe table event ely by3mlo eno hykrr el row da m3 el ba2y 7ta lw msh moshtrken feeeh
    }
    public ResultSet getstudentparticipateineventdata2(String eid)
    {
        return dbsuperman.select("select student.fname,student.sname,student.lname,student.sid,event.name,event.startdate from event,student,sje where student.sid=sje.sid and event.eid=sje.eid and sje.eid="+eid+";");
    }
    public ResultSet getstudentparticipateineventdata3(String sid)
    {
        return dbsuperman.select("select student.fname,student.sname,student.lname,student.sid,event.name,event.startdate from event,student,sje where student.sid=sje.sid and event.eid=sje.eid and sje.sid="+sid+";");
    }
     public ResultSet getguardianparticipateineventdata1(String gid,String eid)
    {
        return dbsuperman.select("select guardian.fname,guardian.lname,guardian.gid,event.name,event.startdate from event,guardian,gje where guardian.gid=gje.gid and event.eid=gje.eid and gje.gid="+gid+" and gje.eid="+eid+";");
    }
    public ResultSet getguardianparticipateineventdata2(String eid)
    {
        return dbsuperman.select("select guardian.fname,guardian.lname,guardian.gid,event.name,event.startdate from event,guardian,gje where guardian.gid=gje.gid and event.eid=gje.eid and gje.eid="+eid+";");
    }
    public ResultSet getguardianparticipateineventdata3(String gid)
    {
        return dbsuperman.select("select guardian.fname,guardian.lname,guardian.gid,event.name,event.startdate from event,guardina,gje where guardian.gid=gje.gid and event.eid=gje.eid and gje.gid="+gid+";");
    }
    public ResultSet selectbusbydestination(String des)
    {
        return dbsuperman.select("select from bus where destination like '%"+des+"%';");
    }
    public ResultSet getstudentparticipateinbusdata1(String sid,String bnumber)
    {
        return dbsuperman.select("select bus.destination,bus.supervisor,student.fname,student.sname,student.lname,student.sid from bus,student where student.sid="+sid+" and bus.bnumber=student.bnumber and bus.bnumber="+bnumber+";");
    }
    public ResultSet getstudentparticipateinbusdata2(String bnumber)
    {
        return dbsuperman.select("select bus.destination,bus.supervisor,student.fname,student.sname,student.lname,student.sid from bus,student where  bus.bnumber=student.bnumber and bus.bnumber="+bnumber+";");
    }
    public ResultSet getstudentparticipateinbusdata3(String sid)
    {
       return dbsuperman.select("select bus.destination,bus.supervisor,student.fname,student.sname,student.lname,student.sid from bus,student where student.sid="+sid+" and bus.bnumber=student.bnumber;");
    }
    public ResultSet getteacherparticipateinbusdata1(String tid,String bnumber)
    {
        return dbsuperman.select("select bus.destination,bus.supervisor,teacher.fname,teacher.sname,teacher.lname,teacher.tid from bus,teacher where teacher.tid="+tid+" and bus.bnumber=teacher.bnumber and bus.bnumber="+bnumber+";");
    }
    public ResultSet getteacherparticipateinbusdata2(String bnumber)
    {
        return dbsuperman.select("select bus.destination,bus.supervisor,teacher.fname,teacher.sname,teacher.lname,teacher.tid from bus,teacher where  bus.bnumber=teacher.bnumber and bus.bnumber="+bnumber+";");
    }
    public ResultSet getteacherparticipateinbusdata3(String tid)
    {
       return dbsuperman.select("select bus.destination,bus.supervisor,teacher.fname,teacher.sname,teacher.lname,teacher.sid from bus,teacher where teacher.tid="+tid+" and bus.bnumber=teacher.bnumber;");
    }
    public ResultSet selectsubjectbyname(String name)
    {
        return dbsuperman.select("select *from subject where name like '%"+name+"%';");
    }
   /* public ResultSet getsubjectandteacherdataforaddteachettoclass(String fname,String mname,String lname,String name)
    {
        if(name.isEmpty())
        {
         String query="select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year from teacher,subject,teach where teacher.tid=teach.tid and subject.code=teach.code and  ";
        boolean found=false; int first=0;
        if(!fname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="fname like '%"+fname+"%'";
            ++first;
            found=true;
        }
        if(!mname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="sname like '%"+mname+"%'";
            ++first;
            found=true;
        }
        if(!lname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="lname like '%"+lname+"%'";
            ++first;
            found=true;
        }
        query+=";";
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        return null;
        }
       
    }
*/
    public ResultSet searchforsubject(String name,String stage,String year,String fullmark,String successmark,String sf,String code)
    {
        boolean foundwhere=false;
    
    //int to count if i were in the first count i will not add ana and
    int firstwhere=0;
    String query="select *from subject where ";
    String apos="'";
    if(!name.isEmpty())
    {
        query+="name="+apos+name+apos; foundwhere=true;
        ++firstwhere;
    }
    if(!stage.equals("0"))
    {
       if(firstwhere>0) {query+=" and ";}
       query+="stage="+apos+stage+apos; 
       foundwhere=true;
       ++firstwhere;
    }
    if(!year.equals("0")){
        if(firstwhere>0)query+=" and ";
        query+="year="+apos+year+apos;
        foundwhere=true;
        ++firstwhere;
    }
    
    if(!fullmark.isEmpty()){
        if(firstwhere>0)query+=" and ";
         query+="fullmark="+fullmark; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!successmark.isEmpty()){
        if(firstwhere>0)query+=" and "; 
         query+="successmark="+successmark; 
        foundwhere=true;
        ++firstwhere;    
    }
    if(!sf.equals("0")){
        if(firstwhere>0)query+=" and ";
        query+="sciencefield="+apos+sf+apos; 
        foundwhere=true;
        ++firstwhere;
    }
    if(!code.isEmpty()){
        if(firstwhere>0)query+=" and ";
        query+="code="+code; 
        foundwhere=true;
        ++firstwhere;
    }
    query+=";";
    System.out.println(foundwhere);
    if(!foundwhere)return null;
    else return dbsuperman.select(query);
    }
    public int updaterowsubject(String name,String stage,String year,String fullmark,String successmark,String sf,String code)
    {
         String comma=","; String apos="'"; 
       if(name.isEmpty())name="null";
       else name=apos+name+apos;
       if(stage.equals("0"))stage="null";
       else stage=apos+stage+apos;
       if(year.equals("0"))year="null";
       else year=apos+year+apos;
       if(fullmark.isEmpty())fullmark="null";
       if(successmark.isEmpty())successmark="null";
       if(sf.equals("0"))sf="null";
       else sf=apos+sf+apos;
       if(code.isEmpty())code="null";
  
         String query="update subject set year="+year+","+"stage="+stage+","+
            "name="+name+comma+"fullmark="+fullmark+comma+
                    "successmark="+successmark+comma+"sciencefield="+sf+
      
                 " where "+
                    "code="+code+";";
        
        return dbsuperman.eid(query);
    }
    public int updaterowssubject(String name,String stage,String year,String fullmark,String successmark,String sf,String code)
    {
        String comma=","; String apos="'"; String dash="-";
                    String query="update subject set ";
                    boolean found=false; int firstset=0; 
                    
     if(!year.equals("0"))
    {
        query+="year="+apos+year+apos; found=true;
        ++firstset;
    }
    if(!stage.equals("0"))
    {
       if(firstset>0) {query+=",";}
        query+="stage="+apos+stage+apos; 
       found=true;
       ++firstset;
    }
    if(!sf.equals("0")){
        if(firstset>0)query+=",";
        query+="sciencefield="+apos+sf+apos;
        found=true;
        ++firstset;
    }
    
   
    if(!fullmark.isEmpty()){
        if(firstset>0)query+=","; 
         query+="fullmark="+fullmark; 
        found=true;
        ++firstset;    
    }
    if(!successmark.isEmpty()){
        if(firstset>0)query+=",";
         query+="successmark="+successmark; 
        found=true;
        ++firstset;
    }
    
    if(!name.isEmpty()){
        if(firstset>0)query+=",";
         query+="name="+apos+name+apos; 
        found=true;
        ++firstset;
    }
    
    query+=" where code="+code;
    query+=";";
    System.out.println(query);
    return dbsuperman.eid(query);
    }
    public ResultSet searchforclassdata(String stage,String year,String number)
    {
        boolean found=false; int first=0;
        String query="select *from class where ";
        if(!stage.equals("0"))
        {
            if(first>0)query+=" and ";
            query+="stage="+"'"+stage+"'";
            ++first;
            found=true;
        }
        if(!year.equals("0"))
        {
            if(first>0)query+=" and ";
            query+="stage="+"'"+stage+"'";
            ++first;
            found=true;
        }
        if(!number.equals("0"))
        {
            if(first>0)query+=" and ";
            query+="stage="+"'"+stage+"'";
            ++first;
            found=true;
        }
        if(found)
        {
            return dbsuperman.select(query);
        }
        return null;
    }
    public int makeorunmakestudentpioneer(String sid,String cid)
    {
        return dbsuperman.eid("update class set sid="+sid+" where cid="+cid+";");
    }
    public ResultSet getdataforaddteachertoclass1(String fname,String sname,String lname ,String subjectname)
    {
        String query="select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year ,teach.teachid from teacher,subject,teach where teach.tid=teacher.tid and teach.code=subject.code and subjectname like '%"+subjectname+"%'";
        if(!fname.isEmpty())
        {
            query+=" and teacher.fname like '%"+fname+"%'";
        }
        if(!sname.isEmpty())
        {
            query+=" and teacher.sname like '%"+sname+"%'";
        }
        if(!lname.isEmpty())
        {
            query+=" and teacher.lname like '%"+lname+"%'";
        }
        query+=";";
        return dbsuperman.select(query);
    }
     public ResultSet getdataforaddteachertoclass2(String fname,String sname,String lname )
    {
        String query="select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year ,teach.teachid from teacher,subject,teach where teach.tid=teacher.tid and teach.code=subject.code";
        if(!fname.isEmpty())
        {
            query+=" and teacher.fname like '%"+fname+"%'";
        }
        if(!sname.isEmpty())
        {
            query+=" and teacher.sname like '%"+sname+"%'";
        }
        if(!lname.isEmpty())
        {
            query+=" and teacher.lname like '%"+lname+"%'";
        }
        query+=";";
        return dbsuperman.select(query);
    }
      public ResultSet getdataforaddteachertoclass3(String subjectname)
    {
        String query="select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year ,teach.teachid from teacher,subject,teach where teach.tid=teacher.tid and teach.code=subject.code and subjectname like '%"+subjectname+"%';";
      
        return dbsuperman.select(query);
    }
    public ResultSet getteachdataforcombobox()
    {
          return dbsuperman.select("select teacher.fname,teacher.sname,teacher.lname,subject.name,subject.stage,subject.year,teach,teachid from teacher,subject,teach where teach.tid=teacher.tid and teach.code=subject.code ;");
    }
    public ResultSet getstudentpioneers(String s,String y,String n)
    {
       String query="select s.fname,s.sname,s.lname,c.stage,c.year,c.number,from student s,class c where s.sid=c.sid ";
       if(!s.equals("0"))
       {
           query+=" and c.stage="+s;
       }
       if(!y.equals("0"))
       {
           query+=" and c.year="+y;
       }
       if(!n.equals("0"))
       {
           query+=" and c.number="+n;
       }
       query+=";";
       return dbsuperman.select(query);
    }
    public ResultSet getteacherpioneers(String s,String y,String n)
    {
       String query="select s.fname,s.sname,s.lname,c.stage,c.year,c.number,from teacher s,class c where s.tid=c.sid ";
       if(!s.equals("0"))
       {
           query+=" and c.stage="+s;
       }
       if(!y.equals("0"))
       {
           query+=" and c.year="+y;
       }
       if(!n.equals("0"))
       {
           query+=" and c.number="+n;
       }
       query+=";";
       return dbsuperman.select(query);
    }
    public ResultSet getcodeandtidformteach(String teachid)
    {
        return dbsuperman.select("select code ,tid where teachid="+teachid+";");
    }
    public ResultSet getteachdata1(String tid,String code,String cs,String cy,String cn)
    {
        String cid=cy+cs+cn;
        return dbsuperman.select("select t.fname,t.sname,t.lname,s.name,c.stage,c.year,c.number from teacher t,subject s,class c,teach e where t.tid=e.tid and s.code=e.code and c.cid=e.cid and e.cid="+cid+" and e.tid="+tid+"and e.code="+code+";");
    }

    
    
    
    
    
    
    
    //________________________________________________________________________________________
    
    
    
    
    
    
    
      
    
    


    public ResultSet searchforstudent(String sid,String fname,String sname,String lname,String sex,String address,String phone,String mail,String bdate,String cid,String gid,String bid )
    {
        String query="select *from student where ";
        boolean found=false; int first=0;
        if(!sid.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="sid= "+"'"+sid+"'";
            ++first;
            found=true;
        }
        if(!fname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="fname= "+"'"+fname+"'";
            ++first;
             found=true;
        }
        if(!sname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="sname= "+"'"+sname+"'";
            ++first;
             found=true;
        }
        if(!lname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="lname= "+lname;
            ++first;
             found=true;
        }
        if(!sex.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="sex= "+sex;
            ++first;
             found=true;
        }
        if(!address.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="address= "+"'"+address+"'";
            ++first;
             found=true;
        }
        if(!phone.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="phone= "+"'"+phone+"'";
            ++first;
             found=true;
        }
        if(!mail.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="mail= "+mail;
            ++first;
             found=true;
        }
        if(!bdate.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bdate= "+bdate;
            ++first;
             found=true;
        }
        if(!cid.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="cid= "+cid;
            ++first;
             found=true;
        }
        if(!gid.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="gid= "+"'"+gid+"'";
            ++first;
             found=true;
        }
        if(!bid.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="bnumber= "+"'"+bid+"'";
            ++first;
             found=true;
        }
        query+=";";
        System.out.println(found);
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        else return null;
    }    
    
    
    
    
    
    
    
    
    
    
    
    public int updaterowstudent(String fname,String sname,String lname,String sid,String address,String phone,String mail ,String sex,String bnumber,String cid , String gid , String bdate)
    {
        String comma=","; String apos="'";
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
//        if(position.isEmpty())position="newbi";
        if(bnumber.isEmpty())bnumber="null";
        else bnumber=apos+bnumber+apos;
        
        
        String query="update student set fname='"+fname+"',sname='"+sname+"',lname='"+lname+"',sid="+sid+",bdate='"+bdate+"',address='"+address+"',phone="+phone+",mail="+mail+",cid="+cid+",bnumber="+bnumber+",gid="+gid+",sex='"+sex+"' where sid="+sid+";";
        return dbsuperman.eid(query);
    }
     public int updaterowsstudent(String fname,String sname,String lname,String sid,String address,String phone,String mail ,String sex,String bnumber,String cid , String gid , String bdate)
    {
        
         String comma=","; String apos="'"; 
                    String query="update teacher set ";
                    boolean found=false; int firstset=0; 
                    
     if(!fname.isEmpty())
    {
        if(firstset>0)query+=",";
        query+="fname="+apos+fname+apos; found=true;
        ++firstset;
    }
    if(!sname.isEmpty())
    {
       if(firstset>0) {query+=",";}
        query+="sname="+apos+sname+apos; 
       found=true;
       ++firstset;
    }
    if(!lname.isEmpty()){
        if(firstset>0)query+=",";
        query+="lname="+apos+lname+apos;
        found=true;
        ++firstset;
    }
    
    if(!sid.isEmpty()){
        if(firstset>0)query+=",";
         query+="sid="+apos+sid+apos; 
        found=true;
        ++firstset;
    }
    if(!address.isEmpty()){
        if(firstset>0)query+=","; 
         query+="address="+apos+address+apos; 
        found=true;
        ++firstset;    
    }
    if(!phone.isEmpty()){
        if(firstset>0)query+=",";
         query+="phone="+apos+phone+apos; 
        found=true;
        ++firstset;
    }
    if(!mail.isEmpty()){
        if(firstset>0)query+=",";
         query+="mail="+apos+mail+apos; 
        found=true;
        ++firstset;
    }
    if(!bnumber.isEmpty()){
        if(firstset>0)query+=",";
         query+="bnumber="+apos+bnumber+apos; 
        found=true;
        ++firstset;
    }
     
    if(!cid.isEmpty()){
        if(firstset>0)query+=",";
         query+="cid="+apos+cid+apos; 
        found=true;
        ++firstset;
    }
    
    if(!gid.isEmpty()){
        if(firstset>0)query+=",";
         query+="gid="+apos+gid+apos; 
        found=true;
        ++firstset;
    }
    if(!bdate.isEmpty()){
        if(firstset>0)query+=",";
         query+="bdate="+apos+bdate+apos; 
        found=true;
        ++firstset;
    }
    
    if(!sex.isEmpty()){
        if(firstset>0)query+=",";
         query+="sex="+apos+sex+apos; 
        found=true;
        ++firstset;
    }
    
   
    query+=" where sid="+apos+sid+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
        
        
    }
    

     
      public ResultSet searchforguardian(String gid,String fname,String lname,String address,String phone,String mail,String job,String workphone )
    {
        String query="select *from guardian where ";
        boolean found=false; int first=0;
        if(!gid.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="gid= "+"'"+gid+"'";
            ++first;
            found=true;
        }
        if(!fname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="fname= "+"'"+fname+"'";
            ++first;
             found=true;
        }
        if(!lname.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="lname= "+"'"+lname+"'";
            ++first;
             found=true;
        }
        if(!address.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="address= "+"'"+address+"'";
            ++first;
             found=true;
        }
        if(!phone.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="phone= "+"'"+phone+"'";
            ++first;
             found=true;
        }
        if(!mail.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="mail= "+mail;
            ++first;
             found=true;
        }
        if(!job.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="jop= "+job;
            ++first;
             found=true;
        }
        if(!workphone.isEmpty())
        {
            if(first>0)query+=" and ";
            query+="workphone= "+"'"+workphone+"'";
            ++first;
             found=true;
        }
        query+=";";
        System.out.println(found);
        System.out.println(query);
        if(found)
        {
            return dbsuperman.select(query);
        }
        else return null;
    }    
    
   
     
    public int updaterowguardian(String gid,String fname,String lname,String address,String phone,String mail,String job,String workphone )
    {
        String comma=","; String apos="'";
        if(phone.isEmpty())phone="null";
        else phone=apos+phone+apos;
        if(mail.isEmpty())mail="null";
        else mail=apos+mail+apos;
//        if(position.isEmpty())position="newbi";
        if(workphone.isEmpty())workphone="null";
        else workphone=apos+workphone+apos;
        
        
        String query="update guardian set fname='"+fname+"',lname='"+lname+"',gid="+gid+",workphone='"+workphone+"',address='"+address+"',phone="+phone+",mail='"+mail+"';";
        return dbsuperman.eid(query);
    }
     
     
     
  public int updaterowsguardian(String gid,String fname,String lname,String address,String phone,String mail,String job,String workphone )
    {
        
         String comma=","; String apos="'"; 
                    String query="update guardian set ";
                    boolean found=false; int firstset=0; 
                    
     if(!fname.isEmpty())
    {
        if(firstset>0)query+=",";
        query+="fname="+apos+fname+apos; found=true;
        ++firstset;
    }
    if(!lname.isEmpty()){
        if(firstset>0)query+=",";
        query+="lname="+apos+lname+apos;
        found=true;
        ++firstset;
    }
    
    if(!gid.isEmpty()){
        if(firstset>0)query+=",";
         query+="gid="+apos+gid+apos; 
        found=true;
        ++firstset;
    }
    if(!address.isEmpty()){
        if(firstset>0)query+=","; 
         query+="address="+apos+address+apos; 
        found=true;
        ++firstset;    
    }
    if(!phone.isEmpty()){
        if(firstset>0)query+=",";
         query+="phone="+apos+phone+apos; 
        found=true;
        ++firstset;
    }
    if(!mail.isEmpty()){
        if(firstset>0)query+=",";
         query+="mail="+apos+mail+apos; 
        found=true;
        ++firstset;
    }
    if(!job.isEmpty()){
        if(firstset>0)query+=",";
         query+="jop="+apos+job+apos; 
        found=true;
        ++firstset;
    }
     
    if(!workphone.isEmpty()){
        if(firstset>0)query+=",";
         query+="workphone="+apos+workphone+apos; 
        found=true;
        ++firstset;
    }
    
    
   
    query+=" where gid="+apos+gid+apos;
    query+=";";
    System.out.println(query);
         return dbsuperman.eid(query);
        
        
    }
     
     
      
     
     
     
     
     
     
     
     
     
     
     
     
     














    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //____________________________________________________________________________________
    
}

