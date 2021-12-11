package com.course.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

// activity_main 레이아웃의 RecyclerView 객체를 불러와서 이를 어댑테와 연결시키는 역할
public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리싸이클러뷰에 레이아웃을 설정해준다. 이 레이아웃은 xml으로 만들어준 내용을 사용한다.
        myRecyclerView = findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);
        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        //아이템들을 목록으로 만들어 준다. 목록은 Arraylist 객체를 사용한다.
        ArrayList<Product> schoolsInfo = new ArrayList<>();
        schoolsInfo.add(new Product(R.drawable.shadow01, R.drawable.shadow01_2, "데이지크 섀도우", "https://dasique.co.kr/product/list.html?cate_no=71", "27200원", "#Blooming Mood Collection\n#한송이의 꽃처럼\n#당신만의 무드를 꽃피워보세요."));
        schoolsInfo.add(new Product(R.drawable.shadow02, R.drawable.shadow02_2, "페리페라 섀도우", "https://www.periperacosmetic.com/ko/ad/film.asp", "175000원", "#무드 PLAY\n#올테이크무드팔레트가 음악이라면?\n#우유 한 방울 탄\n#봄컬러무드"));
        schoolsInfo.add(new Product(R.drawable.shadow03, R.drawable.shadow03_2,"롬앤 섀도우", "https://romand.co.kr/category/eye/76/", "18700원", "#롬앤 베러 댄 팔레트\n#비밀정원"));
        schoolsInfo.add(new Product(R.drawable.cusion01, R.drawable.cusion01_2, "라네즈 쿠션", "https://www.laneige.com/kr/ko/makeup/neo-cushion-matte.html", "26000원", "#묻어나지 않는 밀착쿠션\n#360도 보더라스 디자인\n#원터치 스피닝 리핑\n#유니버설 디자인"));
        schoolsInfo.add(new Product(R.drawable.cusion02, R.drawable.cusion02_2, "에스쁘아 쿠션", "https://www.espoir.com/ko/shop/shop_prd_view.do?i_sProductcd=125100765", "29000원", "#스킨블러 #모공블러"));
        schoolsInfo.add(new Product(R.drawable.lip01, R.drawable.lip01_2,"롬앤 립", "https://romand.co.kr/category/lip/74/%20", "12000원", "#밀크티 벨벳\n#베어쥬시\n#빈티지필터"));
        schoolsInfo.add(new Product(R.drawable.lip02, R.drawable.lip02_2,"베네피트 립", "https://www.benefitcosmetics.com/ko-kr/bestsellers", "11500원", "#베스트셀러 #장미빛"));
        schoolsInfo.add(new Product(R.drawable.lip03, R.drawable.lip03_2,"어뮤즈 립", "https://www.amusemakeup.com/product/dew-tint-02-%EC%82%B0%EB%93%A4/168/category/45/display/1/", "29000원", "#산들\n" + "#꽃물\n" + "#피그 듀\n" + "#화양연화\n" + "#봄 밤"));
        schoolsInfo.add(new Product(R.drawable.liner01, R.drawable.liner01_2,"키스미 아이라이너", "https://www.koreakissme.co.kr/", "199000원", "#블랙 #브라운"));
        schoolsInfo.add(new Product(R.drawable.liner02, R.drawable.liner02_2,"클리오 아이라이너", "https://www.cliocosmetic.com/ko/product/list.asp?sh_cate2=10200", "13600원", "#STAY PERFECT"));

        // 목록을 어댑터에 연결해 준다.
        MyAdapter myAdapter = new MyAdapter(schoolsInfo);
        // 어댑터를 뷰에 연결해 준다.
        myRecyclerView.setAdapter(myAdapter);

        //전화, 이메일, 지도 버튼
        Button button1 = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        //전화번호
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01047565514"));
                startActivity(intent);
            }
        });

        //이메일
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        //구글 맵
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Uri uri=Uri.parse("http://maps.google.com/maps?q="+37.559133+","+126.927824);
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);//이걸 실행해달라
            }
        });
    }

    protected void sendEmail() {
        String[] TO = {"smile_cindy@naver.com"};
        String[] CC = {"smile_cindy@naver.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "제품 문의 메일");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "제품 관련 문의드립니다.");

        try{
            startActivity(Intent.createChooser(emailIntent, "제품 문의 제일"));
            finish();
        } catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(MainActivity.this, "이메일 클라이언트가 없네요", Toast.LENGTH_SHORT).show();
        }
    }

}