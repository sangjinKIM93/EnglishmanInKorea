# EnglishmanInKorea

영단어 학습을 도와주는 알람 앱으로
알람 실행시 사전에 등록한 단어 퀴즈가 나오며 이를 맞춰야 알람이 해제되는 앱입니다.
추가적으로 영어단어장을 관리할 수 있고, 영어 학습 자료를 제공해줍니다.


<br>

<br><br>
    
# 주요 기능  

> ## 1. 알람 목록
> - (등록된 알람이 없을 경우) 알람 생성 가능
> - (동록된 알람이 있을 경우) 수정 및 삭제 가능  
<br>
> &nbsp; # 중점 사항  
> &nbsp; - LiveData를 활용하여 등록된 알람 유무 판단  
> &nbsp; - recyclerView LayoutManager로 flexboxLayout Manager 활용  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783161-e86e9400-9e98-11ea-9f50-29b5a471cae1.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783206-ffad8180-9e98-11ea-94e5-c869bc9dad14.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783211-00deae80-9e99-11ea-8390-1971a67c142a.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783729-01c41000-9e9a-11ea-8f88-ccc04ab1dc95.jpg">
</div>
<br>

> ## 2. 알람 생성
> - 단어 등록 및 시간 설정  
<br>
> &nbsp; # 중점 사항  
> &nbsp; - Alphabet 만 입력되는 CustomEditText 적용  
> &nbsp; - 단어장에서 다중 선택으로 단어 등록 가능

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783161-e86e9400-9e98-11ea-9f50-29b5a471cae1.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783206-ffad8180-9e98-11ea-94e5-c869bc9dad14.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783211-00deae80-9e99-11ea-8390-1971a67c142a.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783729-01c41000-9e9a-11ea-8f88-ccc04ab1dc95.jpg">
</div>
<br>

> ## 3. 알람 실행
> - 알람 실행 및 단어 퀴즈 완료시 알람 해제  
<br>
> &nbsp; # 중점 사항  
> &nbsp; - 음악 재생, 무음 극복, 소리 크기 제어  
> &nbsp; - 틀린 경우 hint를 보여주는 CustomTextView 제작  
> &nbsp; - Oreo 버전 이상에서는 ForegroundService를 통한 음악 재생  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783332-308db680-9e99-11ea-985a-37fdf80fdf5b.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783335-31bee380-9e99-11ea-84c1-e289bc460dd8.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783337-32577a00-9e99-11ea-8516-c4a3013275fc.jpg">
</div>
<br>

> ## 4. 단어장
> - 개인 단어 목록 관리  
<br>
> &nbsp; # 중점 사항  
> &nbsp; - 네이버 단어장을 웹뷰로 연동하여 내 단어장에 추가할 수 있는 기능

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783286-21a70400-9e99-11ea-9179-280a789cd681.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783289-223f9a80-9e99-11ea-8c07-4d7a485a5d33.jpg">
</div>
<br>

> ## 4. 영어 학습 페이지
> - 영어 관련 학습을 할 수 있는 화면  
<br>
> &nbsp; # 중점 사항  
> &nbsp; - News API, 명언 API, localDB의 단어장 데이터를 합쳐서 출력  
> &nbsp; - 로컬 캐시 적용(네트워크 연결이 안 되어도 출력 가능)  
> &nbsp; - 네트워크 통신 및 데이터 입출력시 RxJava 활용  

<div>
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783281-1fdd4080-9e99-11ea-9c7f-bda66cca0fe9.jpg">
  <img width="200" src="https://user-images.githubusercontent.com/62230118/82783285-210e6d80-9e99-11ea-8519-22cc004e4e6b.jpg">
</div>
<br>


