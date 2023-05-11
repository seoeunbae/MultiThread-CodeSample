# MultiThread-CodeSample
멀티쓰레드로직을 샘플링함으로, 인턴 프로젝트를 회고합니다.

- ```Effective Java```참고

<br/>
<br/>

# 📑 기능 요구 사항

**Feat/following**
```
1. 특정 유저를 팔로우 합니다.
2. 특정 유저를 언팔로우 합니다.
```

<br/>

**Feat/like**
```
1. 특정 {에셋/브랜드/스타일링}에 대해 좋아요를 남깁니다.
2. 특정 {에셋/브랜드/스타일링}에 대해 좋아요를 해제니다.
```

<br/>

**Feat/bookmark**
```
1. 특정 {에셋/브랜드/스타일링}을 보관함에 추가합니다.
2. 특정 {에셋/브랜드/스타일링}을 보관함에서 해제합니다.
```

<br/>
<br/>

## 1️⃣ ThreadPoolExecutor의 선택
   
**💡멀티쓰레드 주의 사항💡**
 
> 스레드로 직접 작업하는 것을 삼가야한다고 한다. 
> - -> Executor 프레임워크를 사용함으로써, **작업의 유닛과 스레드풀 Execution 매커니즘이 독립적으로 유지**될 수 있도록 하자.

<br/>
<br/>

작고 가벼운 서비스에서는
```Executors.newCachedThreadPool```
이라는 설정이 따로 필요없는 스레드 풀을 사용하면 좋다.

무거운 서버에서는
```newCachedThreadPool```은...
대기큐가 없이 추가 스레드가 필요한 경우 바로바로 새 스레드를 생성하기에, 성능 문제가 오히려 더 심각해진다고한다.

<br/>

## ✨방안

### a. ```Executors.newFixedThreadPool```사용하기
- -> 고정된 개수의 스레드를 사용하는 풀이다.
### b. ```ThreadPoolExecutor```를 사용  ✅

<br/>
<br/>


## 2️⃣ Thread 클래스

<br/>

### Thread interface 의 종류

1. ```Runnable``` (Thread 클래스의 간소화된 형태)
2. ```Callable``` (Runnable과 유사/반환값이 있고, 임의의 예외 throw 가능!)

<br/>


> ```executorService```를 분리함으로써 executor 정책을 상황에 맞게 수정할 수 있도록 유연성을 얻자.

<br/>


### ```CompletableFuture```
- Future의 구현체로, 추가 여러 기능들도 지원한다.
- 정적 메서드인 runAsync와 supplyAsync를 사용하면, 각각 Runnable과 Supplier 함수형의 CompletableFuture 인스턴스를 생성할 수 있습니다.
- 람다 형식을 통해 반환값을 줄 수 있다.

