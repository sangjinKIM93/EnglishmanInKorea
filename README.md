# EnglishmanInKorea

영단어 학습을 도와주는 알람 앱으로
알람 실행시 사전에 등록한 단어 퀴즈가 나오며 이를 맞춰야 알람이 해제되는 앱입니다.
추가적으로 영어단어장을 관리할 수 있고, 영어 학습 자료를 제공해줍니다.


<br><br>
    
# 중점 사항
- MVVM 패턴 적용
- AAC 활용(ViewModel, LiveData, Room)
- RxJava 활용(네트워크 통신, local DB 입출력)
- 중첩리사이클러뷰 및 각종 customView 제작
- 로컬 캐시 적용(통신이 끊겨도 데이터 제공 )

<br><br>
    
# 기능 설명  

> ## 1. 알람 목록
> - (등록된 알람이 없을 경우) 알람 생성 가능
> - (동록된 알람이 있을 경우) 수정 및 삭제 가능  
> 
> &nbsp; # 중점 사항  
> &nbsp; - LiveData를 활용하여 등록된 알람 유무 판단  
> &nbsp; - recyclerView LayoutManager로 flexboxLayout Manager 활용  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783161-e86e9400-9e98-11ea-9f50-29b5a471cae1.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783206-ffad8180-9e98-11ea-94e5-c869bc9dad14.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783211-00deae80-9e99-11ea-8390-1971a67c142a.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/83261458-51863c80-a1f6-11ea-8c82-ec6432147abe.jpg">
</div>
<br><br>

> ## 2. 알람 생성
> - 단어 등록 및 시간 설정  
>
> &nbsp; # 중점 사항  
> &nbsp; - Alphabet 만 입력되는 CustomEditText 적용  
> &nbsp; - 단어장에서 다중 선택으로 단어 등록 가능

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82787115-be20d480-9ea0-11ea-9795-0b2cd4be1ebf.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82787123-bf520180-9ea0-11ea-9665-7ff1d76956e8.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82787127-bfea9800-9ea0-11ea-8482-b6737d42843e.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82787128-bfea9800-9ea0-11ea-8dce-5fea75e219e6.jpg">
</div>
<br><br>

> ## 3. 알람 실행
> - 알람 실행 및 단어 퀴즈 완료시 알람 해제  
>
> &nbsp; # 중점 사항  
> &nbsp; - AlarmManager를 통한 알람 음악 재생
> &nbsp; - 틀린 경우 hint를 보여주는 CustomTextView 제작  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783332-308db680-9e99-11ea-985a-37fdf80fdf5b.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783335-31bee380-9e99-11ea-84c1-e289bc460dd8.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783337-32577a00-9e99-11ea-8516-c4a3013275fc.jpg">
</div>
<br><br>

> ## 4. 단어장
> - 개인 단어 목록 관리  
>
> &nbsp; # 중점 사항  
> &nbsp; - 네이버 단어장을 웹뷰로 연동하여 내 단어장에 추가할 수 있는 기능  
> &nbsp; - Room을 활용한 데이터   

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783286-21a70400-9e99-11ea-9179-280a789cd681.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783289-223f9a80-9e99-11ea-8c07-4d7a485a5d33.jpg">
</div>
<br><br>

> ## 5. 영어 학습 페이지
> - 영어 관련 학습을 할 수 있는 화면  
>
> &nbsp; # 중점 사항  
> &nbsp; - News API, 명언 API, localDB의 단어장 데이터를 합쳐서 출력  
> &nbsp; - 로컬 캐시 적용(네트워크 연결이 안 되어도 출력 가능)  
> &nbsp; - 네트워크 통신 및 데이터 입출력시 RxJava 활용  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783281-1fdd4080-9e99-11ea-9c7f-bda66cca0fe9.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783285-210e6d80-9e99-11ea-8519-22cc004e4e6b.jpg">
</div>
<br><br>


