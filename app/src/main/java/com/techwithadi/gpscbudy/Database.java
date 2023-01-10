package com.techwithadi.gpscbudy;

import android.util.ArrayMap;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.techwithadi.gpscbudy.Adepters.CatAdepter;
import com.techwithadi.gpscbudy.Adepters.TestAdepter;
import com.techwithadi.gpscbudy.Models.CategoryModel;
import com.techwithadi.gpscbudy.Models.ProfileModals;
import com.techwithadi.gpscbudy.Models.QuestionModel;
import com.techwithadi.gpscbudy.Models.TestModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Database {
  public static FirebaseFirestore firestore;
  public static ArrayList<CategoryModel> Sub_list= new ArrayList<>();
    public static ArrayList<TestModel> test_list= new ArrayList<>();
  CatAdepter catAdepter;
  public static TestAdepter testAdepter;
  public static int i;
  public static ProfileModals myprofile=new ProfileModals("Your Name","Your Email");

  public static ArrayList<QuestionModel> Question_list=new ArrayList<>();


  public static void createuser(String email,String name , ComplateListners complateListners){
      Map<String,Object> userdata = new ArrayMap<>();

      userdata.put("EMAIL_ID",email);
      userdata.put("NAME",name);
    //  userdata.put("PASS",pass);
      userdata.put("TOTAL_SCORE",0);

      DocumentReference userdoc = firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
      WriteBatch batch=firestore.batch();
      batch.set(userdoc,userdata);     // Update the data of User

      DocumentReference usercount = firestore.collection("USERS").document("TOTAL_USERS");
      batch.update(usercount,"COUNT", FieldValue.increment(1)); //Increment user count on Register

      batch.commit()
              .addOnSuccessListener(new OnSuccessListener<Void>() {
                  @Override
                  public void onSuccess(Void unused) {
                      complateListners.OnSuccess();
                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      complateListners.OnFailure();
                  }
              });


  }



  public static void loadsubject(ComplateListners complateListners){
      Database.Sub_list.clear();

      firestore.collection("SUBJECT").get()
              .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                  @Override
                  public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                      if (queryDocumentSnapshots != null) {
                          ArrayList<DocumentSnapshot> sublist = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();


                          for (DocumentSnapshot d : sublist) {
                              String cat_id = d.getString("CAT_ID");
                              String cat_name = d.getString("NAME");
                              Long no_of_test = d.getLong("NO_OF_TEST");
//                          CategoryModel ob=d.toObject(CategoryModel.class);
                              Sub_list.add(new CategoryModel(cat_id, cat_name, no_of_test));
                          }


                          complateListners.OnSuccess();

                      }
                  }

              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      complateListners.OnFailure();
                  }
              });
  }

  public static void  loadtestdata(String docid,ComplateListners complateListners){
      Database.test_list.clear();
      CollectionReference collectionRef = firestore.collection("SUBJECT");
      DocumentReference docRef = collectionRef.document(docid);
      CollectionReference subcollectionRef = docRef.collection("TEST_LIST");

             subcollectionRef.get()
              .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                  @Override
                  public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                          List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                          for (DocumentSnapshot d : list) {

                              String test_id= d.getString("TEST_ID");
                              Long test_time=d.getLong("TEST_TIME");
                              test_list.add(new TestModel(test_id,0, test_time));

                          }

                          complateListners.OnSuccess();

                      }

              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      complateListners.OnFailure();
                  }
              });

  }

  public static void loaddata(ComplateListners complateListners){
      loadsubject(complateListners);

      firestore.collection("USERS").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get()
              .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                  @Override
                  public void onSuccess(DocumentSnapshot documentSnapshot) {
                      myprofile.setName(documentSnapshot.getString("NAME"));
                      myprofile.setEmail(documentSnapshot.getString("EMAIL_ID"));
                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {

                  }
              });
  }

  public static void loadquestions(ComplateListners complateListners,String cat_id,String test_id){

      Question_list.clear();
      CollectionReference collectionRef = firestore.collection("SUBJECT");
      DocumentReference docRef = collectionRef.document(cat_id);
      CollectionReference subcollectionRef = docRef.collection("TEST_LIST");

      DocumentReference ref1 = subcollectionRef.document(test_id);
      CollectionReference Questionref=ref1.collection("QUESTIONS");
      Questionref.get()
              .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                  @Override
                  public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                      for (DocumentSnapshot doc: queryDocumentSnapshots){
                          String que=doc.getString("QUE");
                          String a=doc.getString("A");
                          String b=doc.getString("B");
                          String c=doc.getString("C");
                          String d=doc.getString("D");
                          Long ans=doc.getLong("ANS");

                          Question_list.add(new QuestionModel(que,a,b,c,d, ans.intValue()));
                      }

                      complateListners.OnSuccess();
                  }
              })
              .addOnFailureListener(new OnFailureListener() {
                  @Override
                  public void onFailure(@NonNull Exception e) {
                      complateListners.OnFailure();
                  }
              });
  }

}
