/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mdawod
 */
public class query {

    String q ;
    dbmanager dbman ;
     ResultSet  res ;
    public query(){
        dbman = new dbmanager();
    }
    
    public Boolean login(String user, String pass){
        
        try {
            q = "SELECT * FROM Users_Basic";
            res = dbman.execute(q);
            while (res.next()){
                if(((res.getString("username")).equals(user)) && ((res.getString("password")).equals(pass)))
                    return true;
                
            }   } catch (SQLException ex) {
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
        }

            return false;
}
    
    public ResultSet getstudent(String sid){
        q = "select * from student where sid=\""+sid+"\";";
        res = dbman.execute(q);
        return res;
    }
    public  ResultSet getclass(String cid){
        q="select sciencefield from class where cid="+cid+";";
        res =dbman.execute(q);
        return res;
    }
    
    public ResultSet getgrades(String sid){
        q="select g.* , s.name from grades g , subject s where g.sid=\""+sid+"\" and s.code=g.code;";
        res =dbman.execute(q);
        return res;
    }
    
    
    public  ResultSet getevents(String sid){
        q="select * from event e ,sje s where sid=\""+sid+"\" and e.eid=s.eid;";
        res = dbman.execute(q);
        return res;
    }
    
    
    public ResultSet getteachers(String sid){
        q = "select t.* , j.name from teacher t , subject j , student s , teach h where s.sid=\""+sid+"\" and s.cid=h.cid and h.code=j.code and h.tid=t.tid;";
        res= dbman.execute(q);
        return res;
    }
    
    public  ResultSet geteval(String sid){
        q="select j.name , j.successmark , j.fullmark , g.f1+g.f2 from student s , grades g , subject j ,teach t where s.sid=\""+sid+"\" and s.cid=t.cid and  t.code=j.code and  j.code=g.code  and  s.sid=g.sid;";
        res = dbman.execute(q);
        return res;
    }
    
    
     public ResultSet getstdforcls( String stg ,String yer, String num){
      
         q="select s.sid ,fname , sname , lname from student s, class c where year='"+yer+"' and stage='"+stg+"' and number='"+num+"' and c.cid=s.cid ;";
        res =dbman.execute(q);
        return res;
    }
     
     public boolean  insert_attend(String sid , char c , String d , String m , String y){
         q="insert into absence values("+sid+","+c+",'"+y+"-"+m+"-"+d+"');";
         try{
         res = dbman.execute(q); 
         }
         catch(Exception e){
             return false;
         }
         
         return true;
     }
   
     public boolean insert_punc_alarm(String sid , int b){
         try{
             q= "select punctionality from attendance where sid="+sid+";"; 
             res=dbman.execute(q);
             res.next();
             String punc = res.getString("punctionality");
             int punci = Integer.parseInt(punc);
             
             q= "select alarms from attendance where sid="+sid+";"; 
             res=dbman.execute(q);
             res.next();
             String alarm = res.getString("alarms");
             int alarmi = Integer.parseInt(alarm);
             
             if(b==1)
             q="insert into attendance values("+sid+","+punci+","+(int)(alarmi+b)+")";
             else
             q="insert into attendance values("+sid+","+(int)(punci+b)+","+alarmi+")";
                 
             try{
                 res = dbman.execute(q);
             }
             catch(Exception e){
                 return false;
             }
             
             
             
             
         }
         catch(SQLException ex){
            Logger.getLogger(query.class.getName()).log(Level.SEVERE, null, ex);
            return false;
         }
         
         return true;
     }
    
    
    
    
    
    
    //______________________HAMADA______________________________
    
        public ResultSet getAllClassesInDB()
    {
        q = "select cid from class;";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet ShowTeacherInfo(String id)
    {
        q = "select * from teacher where tid = " + id +";";
        res = dbman.execute(q);
        return res;
    }
    public void EditTeacherContactInfo(String id,String mail,String phone,String address)
    {
        q = "update teacher set ";
        if(!id.isEmpty())
        {
            if(!mail.isEmpty())
                dbman.eid(q + "mail = '"+mail+"'  where tid = " + id + ";");
            if(!phone.isEmpty())
                dbman.eid( q + "phone = '"+phone+"' where tid = " + id + ";");
            if(!address.isEmpty())
                dbman.eid(q +"address = '" + address +"' where tid = " + id + ";");
        }
    }
    
    public ResultSet getClassCodeTeachid(String id)
    {
        q = "select cid , code , teachid from teach where tid = " + id + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getSubjectName(String cd)
    {
        q = "select name from subject where code = '" + cd + "'";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getDayPeriod(String teachid)
    {
        q = "select day , period from time where teachid = " + teachid + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getTeacherClasses(String tid)
    {
        q = "select cid from teach where tid = " + tid + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getSubjectOfTeacherClass(String tid,String cid)
    {
        q = "select code from teach where tid = " + tid + " and cid = " + cid + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getClassInfo(String cid)
    {
        q = "select cid , sciencefield, csize , capacity , level from class where " +
                "cid = " + cid + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getAllStudentInClass(String cid)
    {
        q = "select sid as 'Student ID',s.fname as 'Student Name',s.sname,s.lname ,g.phone as 'Guardian Phone'\n" +
            ",g.mail as 'Guardian Mail' from student s,guardian g where s.gid = g.gid and cid = " + cid + ";";
        res = dbman.execute(q);
        return res;
    }
    
    public ResultSet getGradesOfClassInSubject(String code,String cid)
    {
        q = "select g.sid,g.yw1,g.mt1,g.f1,g.yw2,g.mt2,g.f2 from grades g, student s where "
                + "g.sid = s.sid and s.cid = " + cid +" and g.code = '" + code +"';";
        res = dbman.execute(q);
        return res;
    }
    
    public void UpdateGrades(String id,String Str,String mark,String code)
    {
        q = "update grades set " + Str + " = " + mark + " where sid = " + id + " and code = '" + code + "';";
        dbman.eid(q);
    }
    
    public void AddNewUnable(String id,String cs,String ht)
    {
        if(id.isEmpty())
            id="null";
        if(cs.isEmpty())
            cs="null";
        else
            cs="'"+cs+"'";
        if(ht.isEmpty())
            ht="null";
        else
            ht="'"+ht+"'";
           
        q = "insert into unable values(" + id + "," + cs + "," + ht  + "," + id + ");";
        dbman.eid(q);
    }
    
    public void DeleteUnable(String id,String cs,String ht)
    {
        q = "delete from unable where ";
        if(id.isEmpty())
        {
            if(cs.isEmpty() && !ht.isEmpty())
            {
                q += "helptype = '" + ht + "';";
            }
            else if(!cs.isEmpty() && ht.isEmpty())
            {
                q += "casetype = '" + cs + "';";
            }
            else if(!cs.isEmpty() && !ht.isEmpty())
            {
                q += "helptype = '" + ht + "' and casetype = '" + cs + "';";
            }
            else
            {
                return;
            }
        }
        else
        {
            q += "unsid = " + id + ";";
        }
        dbman.eid(q);
    }
    
    public ResultSet ShowUnable()
    {
        q = "select sid,casetype,helptype,dname from unable U,donate D where U.unsid = D.unsid;";
        res = dbman.execute(q);
        return res;
    }
    
    public void EditUnable(String id,String cs,String ht)
    {
        if(!id.isEmpty())
        {
            q = "update unable set ";
            if(!cs.isEmpty() && ht.isEmpty())
            {
                q += "casetype = '" + cs + "' where sid = " + id + ";";
            }
            else if(cs.isEmpty() && !ht.isEmpty())
            {
                q += "helptype = '" + ht + "' where sid = " + id + ";";
            }
            else if(!cs.isEmpty() && !ht.isEmpty())
            {
                q += "casetype = '" + cs + "' , helptype = '" + ht + "' where sid = " + id + ";";
            }
            else
            {
                return;
            }
            dbman.eid(q);
            
        }
    }
    
    public void AddNewDonor(String Dname,String cont,String notes)
    {
        if(Dname.isEmpty()||cont.isEmpty())
            return;
        if(notes.isEmpty())
            notes="nothing";    
        
        q = "insert into donor values('"+ Dname + "','" + cont + "','" + notes  + "' );";
        dbman.eid(q);
    }
    
    public void DeleteDonor(String Dname,String cont,String notes)
    {
        q = "delete from donor where ";
        if(Dname.isEmpty() && !cont.isEmpty())
        {
            q += "contact = '" + cont + "'";
        }
        else if(!Dname.isEmpty() && cont.isEmpty())
        {
            q += "name = '" + Dname + "'";
        }
        else if(!Dname.isEmpty() && !cont.isEmpty())
        {
            q += "contact = '" + cont + "' and name = '" + Dname  + "';";
        }
        else
        {
            return;
        }
        dbman.eid(q);
    }
    
    public ResultSet ShowDonor()
    {
        q =  "select name,contact,notes,count(*) from donor group by name;";
        res = dbman.execute(q);
        return res;
    }
    
    public void EditDonor(String name,String cont,String notes)
    {
        if(!name.isEmpty())
        {
            q = "update donor set ";
            if(!cont.isEmpty() && notes.isEmpty())
            {
                q += "contact = '" + cont + "' where name = '" + name + "';";
            }
            else if(cont.isEmpty() && !notes.isEmpty())
            {
                q +="notes = '" + notes + "' where name = '" + name + "';";
            }
            else if(!cont.isEmpty() && !notes.isEmpty())
            {
                q += "contact = '" + cont + "' , notes = '" + notes + "' where name = '" + name + "';";
            }
            else
            {
                return;
            }
            dbman.eid(q);
            
        }
    }
    
    public void AddNewSuper(String id,String talent,String achv)
    {
        if(id.isEmpty())
            id="null";
        if(talent.isEmpty())
            talent="null";
        else
            talent="'"+talent+"'";
        if(achv.isEmpty())
            achv="null";
        else
            achv="'"+achv+"'";
           
        q = "insert into super values(" + id + "," + talent + "," + achv  + ");";
        dbman.eid(q);
    }
    
    public void DeleteSuper(String id,String talent,String achv)
    {
        q = "delete from super where ";
        if(id.isEmpty())
        {
            if(talent.isEmpty() && !achv.isEmpty())
            {
                q += "achievement = '" + achv + "';";
            }
            else if(!talent.isEmpty() && achv.isEmpty())
            {
                q += "talent = '" + talent + "';";
            }
            else if(!talent.isEmpty() && !achv.isEmpty())
            {
                q += "talent = '" + talent + "' and achievement = '" + achv + "';";
            }
            else
            {
                return;
            }
        }
        else
        {
            q += "sid = " + id + ";";
        }
        dbman.eid(q);
    }
    
    public ResultSet ShowSuper()
    {
        
        q = "select * from super;";
        res = dbman.execute(q);
        
        return res;
    }
    
    public void EditSuper(String id,String talent,String achv)
    {
        if(!id.isEmpty())
        {
            q = "update super set ";
            if(!talent.isEmpty() && achv.isEmpty())
            {
                q += "talent = '" + talent + "' where sid = " + id + ";";
            }
            else if(talent.isEmpty() && !achv.isEmpty())
            {
                q += "achievement = '" + achv + "' where sid = " + id + ";";
            }
            else if(!talent.isEmpty() && !achv.isEmpty())
            {
                q += "talent = '" + talent + "' , achievement = '" + achv + "' where sid = " + id + ";";
            }
            else
            {
                return;
            }
            dbman.eid(q);
            
        }
    }
    public void AddNewDonation(String Dname,String id)
    {
        if(!Dname.isEmpty() && !id.isEmpty())
        {
            q = "insert into donate values('"+ Dname + "'," + id +");";
            System.out.println(q);
            dbman.eid(q);
        }
    }
    public void DeleteDonation(String Dname,String id)
    {
        if(!Dname.isEmpty() && !id.isEmpty())
        {
            q = "delete from donate where dname = '" + Dname + "' and unsid = " + id + ";";
            dbman.eid(q);
        }
    }
    
    //________________________HAMADA________________________________________
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}