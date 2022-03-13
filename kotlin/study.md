#Study Kotlin

fun main() {
    //println("Hello, world!!!")
    //helloWorld()
    //val name ="name!"
    //println("my name is ${name} 23")
    //
    //maxBy3(90)
    
    forAndWhile()
    
}

fun maxBy2(a:Int , b:Int):Int = if(a>b) a else b

//조건문 if, when 
fun maxBy3(score : Int){
	
    if(score>10){
        
    }else{
        
    }
    
    when(score){
        0->println("0임")
        1->println("1임")
        2,3,4,5->println("2345임")
    }
    
    var b: Int = when(score){
        1-> 1
        2-> 2
        else -> 3
    }
    
    when(score){
        in 90..100 -> println("you are genius")
        in 10..80 -> println("you are soso")
        else -> println("okay")
    }
    
}
//expression vs Statement
//
//값을 만들면 expression (코틀린의 모든함수는
// 다 expression,
// void타입도 Unit으로 값을 반환하기 때문)
//java void는 statement임(명령만 실행)
//

//컬렉션(배열, 리스트)
fun array(){
    val array :Array<Int> = arrayOf(1,2,3)
    val list : List<Int> = listOf(1,2,3)
    
    //Any로 다양한 타입 넣기 가능
    val array2 : Array<Any> = arrayOf(1,"D",3)
    val list2 : List<Any> = listOf(1,"D",11L)
    
    array[0] =3
    //List는 인터페이스라서 수정이 불가하고 가져오는것만 가능
    var result : Int = list.get(0)
    
    // mutableList는 수정가능, 기본 List는 수정불가
    
    //val은 주소값만 못바꾸는것이기 때문에 추가, 삭제는 문제없다.
    val arrayList : ArrayList<Int> = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)
    
}


//3. 반복문
fun forAndWhile(){
    
    val resultList : ArrayList<String> = 
    arrayListOf("aaa","bbb","ccc","ddd")
    
  //  for(name : String in resultList){
  //      println("${name}")
  //}
    
    var sum : Int = 0
    
    for(i : Int in 1..10 ){//step 2
        sum+= i
        println(j)
    }
 //   for(i : Int in 10 downTo 1){
 //       sum+= i
 //       println(sum)
 //   }
    
  //  for(i : Int in 1 until 100){ //100 미만
  //      sum+= i
   //     println(i)
  //  }
    
}



//7. nulable / NonNull

//NPE
//자바는 컴파일 시점이 아닌 런타임 시점에서 NPE를 발생시킴
//코틀린에서 이것을 컴파일 시점에서 잡기 위해 ? 등장함

var nullName : String? = null // null 가능, 물음표 없으면 null 못넣음

var nullNameInUpperCase  = nullName?.toUpperCase() // 객체옆에 ? 붙여서 null이면 null 반환 null 아니면 함수실행


// ?: 앨비스 프레슬리 연산자
// null 말고 default 값을 주고싶을 때 사용

 val lastName : String? = null
 
 val fullName : String = name+" "+ (lastName?: "NO lastName")// null 일때 "NO lastName" 출력
 
 
 // !!  Null 아님을 알려주는 연산자
 
 fun ignoreNulls(str : String?){
  val mNotNull : String = str!! //원래는 str에 null 가능성이 있기 때문에 올수 없지만, !! 사용으로 들어올 수 있다. 하지만 사용을 지양해야함. 왜냐하면 혹여 Null이 들어올 시 NPE를 발생시키기 때문이다.
  
 }
 
 fun letTest(){
    val email : String? = "abc@naver.com"
    email?.let{
        println("my email is ${it}")
    }
    println("my email is ${email}")
	//println("my email is ${it}")//에러
	
	//let은 객체를 람다 내부로 옮겨준다. it으로 사용 가능하다
	//객체?.let 는 null 이 아닐때만 실행. null이면 실행안함
}



8. Class

//코틀린은 기본적으로 final class이기 때문에 상속을 하려면 open 키워드를 써줘야 한다.
//constructor 생략가능         //주 생성자
open Class Human constructor( val name:Sring = "Anonymous"){
	//val name = "name"
	
	//생성자 오버로딩 (부 생성자) 주생성자 실행 후 실행
	constructor(name : String, age:Int): this(name){//주 생성자에서 상속받음
		println("my name is ${name} , ${age} years old"
	}
	
	//생성자에 코드블럭 넣을 때  //이게 제일먼저 실행
	init{
		println ("New Human has benn born!!")
	}
	
	fun eatingCake(){
		println("gogo")
		
	}
	open fun sintASong(){ //상속시 open해야함
		println("라라")
	}
}

fun main(){
	//val human = Human("Minsoo") //자바는 Human human = new Human("Minsoo");
	//human.eatingCake()
	
	val mom = Human("Kuri",52)
}


//9. 상속

Class Korean : Human() { //자바는 extends Human
	
	override fun singASong(){
		super.singASong() //부모 메서드
		println("라라라")
		println("My name is ${name}")
	}
}

reference : https://medium.com/@limgyumin/%EC%BD%94%ED%8B%80%EB%A6%B0-%EC%9D%98-apply-with-let-also-run-%EC%9D%80-%EC%96%B8%EC%A0%9C-%EC%82%AC%EC%9A%A9%ED%95%98%EB%8A%94%EA%B0%80-4a517292df29


