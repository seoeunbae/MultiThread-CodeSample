# MultiThread-CodeSample
멀티쓰레드로직을 샘플링함으로, 과거(P사) 프로젝트를 회고합니다.

- [1. 기능 상세](#📑-기능-요구-사항)
- [2. 플로우 차트](#📍-플로우차트)
- [3. 개선사항 목록](#🚧-개선사항-목록)
- [4. 어플리케이션 구현 상세정보](#📢-상세설명)
    
    - [a. ExecutorService](#1️⃣-threadpoolexecutor의-선택)
    - [b. CompletableFuture](#2️⃣-thread-클래스)
    - [c. AtomicReference](#3️⃣-atomicreference-사용)



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
2. 특정 {에셋/브랜드/스타일링}에 대해 좋아요를 해제합니다.
```

<br/>

**Feat/bookmark**
```
1. 특정 {에셋/브랜드/스타일링}을 보관함에 추가합니다.
2. 특정 {에셋/브랜드/스타일링}을 보관함에서 해제합니다.
```

<br/>
<br/>


# 📍 플로우차트 

<br/>

![Untitled Diagram](https://github.com/seoeunbae/MultiThread-CodeSample/assets/71380240/d68bec61-c39b-42cd-b379-579bbe57dea0)

<br/>
<br/>

# 🚧 개선사항 목록

**[1. 동시성 이슈 서베이 후 적용](#3️⃣-atomicreference-사용)**
**[2. ABA문제 방지](#atomicreference의-문제-예방)**


<br/>
<br/>

# 📢 상세설명 

## 1️⃣ ThreadPoolExecutor의 선택

- ```Effective Java```참고

<br/>

   
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
<br/>


### ```CompletableFuture```
- Future의 구현체로, 추가 여러 기능들도 지원한다.
- 정적 메서드인 runAsync와 supplyAsync를 사용하면, 각각 Runnable과 Supplier 함수형의 CompletableFuture 인스턴스를 생성할 수 있습니다.
- 람다 형식을 통해 반환값을 줄 수 있다.

<br/>
<br/>

## 3️⃣ ```AtomicReference``` 사용

비동기 처리의 위험성인 스레드 세이프티한 업데이트가 발생할수있도록
 ```AtomicReference```을 사용.

<br/>

Java의 동기화 방법에는 여러가지가 있다.

> - Blocking
>    - Synchronized 사용
> - Non-Blocking
>    - Atomic타입 사용

<br/>

    (이 외에도 다양한 방법이 존재 ex.ThreadLocal, Concurrent...)

<br/>
<br/>

### 1. Synchronized방식 X

```Synchronized```은 락을 해주는 방식이었기 떄문에, 
- 객체단위로 블록을 설정해줘야 한다던지
- 참조 객체를 업데이트를 하면 안된다던지 (락이 안걸려버린다)

고려해줘야하는 부분이 많았으며, 

**결정적으로는 ```Synchronized```는 블로킹 방식이기에 비동기처리의 목적이었던 성능과 맞지 않았다.**

<br/>
<br/>

### 2. Thread-Local방식 X

```ThreadLocal``` 또한 스레드풀 사용환경에서 해당 스레드가 재사용될때 ,
사용자A의 데이터가 남아있는경우, 다른 사용자에게 A의 데이터가 응답될수있어, 매번 삭제를 해줘야하는 문제가있었다. 

**개인정보가 보여질 수 있는 치명적인 문제**가 있었으므로, 이것도 스킵.

<br/>
<br/>

### AtomicReference의 문제 예방

<br/>

**이 프로젝트에 사용된 ```AtomicReference```의 문제는 ```A-B-A 문제```였는데, 이를 위해서 이미 ```AtomicStampedReference```가 있었다.**

timestamp나 versionNumber를 통해서, A-B-A문제를 예방한다고 한다.


<br/>
<br/>




