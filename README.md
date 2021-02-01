# Substitution_cipher_tool
Substitution cipher tool - Gui program (javaFx)
치환 암호 해석 프로그램  
![프로그램](https://user-images.githubusercontent.com/58020519/106418507-d0282d80-6499-11eb-9b74-295e0c3869fb.png)

## 평문을 찾는 방법

단어 빈도수의 정보는 해당 링크로 남겨두겠다. 
(https://www.wordfrequency.info/free.asp?s=y&fbclid=IwAR26zG455Kygsb5YQZYl3JsnI9eylgLhTgPlkzb3lIYOiqt-0k3juIafRLs)

### 1. 가장 먼저 1글자 단어를 찾는다. 한 글자 단어는 i와 a둘 중 하나이며 a가 단어 빈도수에서 도 높기 때문에 m의 key를 a로 둔다.
![1](https://user-images.githubusercontent.com/58020519/106418729-56447400-649a-11eb-94e4-85cb2b45a95c.png)

### 2. 알파벳 중 빈도수가 가장 높은 것은 e이며 단어 중 빈도수가 가장 높은 것은 the이다. ofy 와 y의 빈도수가 가장 높으므로 y의 key가 e임을 유추 할 수 있다.
o = t, f = h, y = e 이다.  
![2](https://user-images.githubusercontent.com/58020519/106418770-69574400-649a-11eb-99ba-18b69d88e421.png)

### 3. the를 찾았기 때문에 to 나 of 그리고 the로 시작하는 단어를 찾는다. (ex : there, they 등). 영어 단어 빈도수 중에서 to의 빈도수가 높기 oc가 to라고 유추할 수 있다. 이후 cp의 빈도수도 높기 때문에 cp도 of라고 유추할 수 있다. 
![3-1](https://user-images.githubusercontent.com/58020519/106418781-6e1bf800-649a-11eb-846f-fd325a7c6f2b.png)

이후 the와 유사한 단어를 찾아보면 ofyt, ofydy, ofdyy, ofyjy가 있다. 먼저 ofydy와 ofdyy를 보면 the_e, th_ee이므로 d는 r을 유추할 수 있다. 그리고 ofyjy는 these이므로 j는 s을 유추할 수 있다.  
![3-2](https://user-images.githubusercontent.com/58020519/106418793-74aa6f80-649a-11eb-8685-3c16658a72b8.png)

ofyt는 the_이므로 t는 n, m, y가 올 수 있기 때문에 나중에 다시 보겠다.  
![3-3](https://user-images.githubusercontent.com/58020519/106418800-7a07ba00-649a-11eb-83f0-bc07205bef44.png)

### 4. 다시 두 글자 단어를 보면 so와 sj가 있다.
![4](https://user-images.githubusercontent.com/58020519/106418812-80963180-649a-11eb-83be-b9bf76970cf0.png)  
_t 와 _s로 끝나는 단어는 it와 is라는 것을 유추 할 수 있으며 현재 암호문에서 s의 빈도수가 높다. 알파벳에서 i의 빈도수 또한 높기 때문에 s가 i라고 유추 할 수 있다.

### 5. 문장 내 단어를 보고 유추하기.
we vse the 다른 문장을 보고 v는 u라는 것을 알 수 있다.  
fcwyhyd가 howehed인것을 보고 however인 것을 알 수 있다.  
![5](https://user-images.githubusercontent.com/58020519/106418822-84c24f00-649a-11eb-824d-a8ef81c69f8e.png)  

맨 첫 단어 jcty는 so_e이므로 some, 즉 t는 m이라는 것을 알 수 있다.  
some 다음 단어인 jyevdsoq 는 se_uritq, security이므로 e = c, q = y, v = u  

### 6. 다시 2글자 단어를 확인 해보면 sr과 mr을 보고 i_, a_이므로 in과 an인 것을 알 수 있다.
![6-1](https://user-images.githubusercontent.com/58020519/106418836-8ab83000-649a-11eb-93c3-1fe3f0b8daef.png)  

이후 zy와 zq을 보고 _e, _y이므로 z가 b인 것을 알 수 있다.  
![6-2](https://user-images.githubusercontent.com/58020519/106418849-8ee44d80-649a-11eb-8361-a4a65879b648.png)  


### 7. 문장 내 단어를 보면
vjsra 는 usin_ 이다. a 는 g라는 것을 유추 할 수 있다.  
 ![7-1](https://user-images.githubusercontent.com/58020519/106418865-999ee280-649a-11eb-8f75-a16f3c14f131.png)  

oclmq는 to_ay이다. l 은 d라는 것을 유추 할 수 있다.  
![7-2](https://user-images.githubusercontent.com/58020519/106418874-9c99d300-649a-11eb-82ce-fb3fee1fe93e.png)  

jqjoyt는 syste_이다. t는 m이라는 것을 유추 할 수 있다.  
![7-3](https://user-images.githubusercontent.com/58020519/106418899-a7546800-649a-11eb-8e05-87e2332fbf99.png)  

tmxy가 ma_e이고 xyq가 _ey이다. x가 k라는 것을 유추 할 수 있다.  
![7-4](https://user-images.githubusercontent.com/58020519/106418946-b804de00-649a-11eb-8ce8-86c724e8265c.png)  

efmrryb는 channe_이다. b가 l이라는 것을 유추 할 수 있다.  
![7-5](https://user-images.githubusercontent.com/58020519/106418954-bd622880-649a-11eb-99bb-b7f5624d02b6.png)  

fcwyhyd가 ho_ever이고 wdsosra가 _riting이므로 w는 w라는 것을 유추 할 수 있다.  
![7-6](https://user-images.githubusercontent.com/58020519/106418961-c2bf7300-649a-11eb-86f0-0d60c468eb02.png)  

nvzbse가 _ublic이고 ndshmoy가 _rivate이므로 n는 p라는 것을 유추 할 수 있다.  
![7-7](https://user-images.githubusercontent.com/58020519/106418973-c8b55400-649a-11eb-869a-62001983f5f5.png)  

ygeynoscrj가 e_ceptions이므로 g는 x라는것을 유추 할 수 있다.  
![7-8](https://user-images.githubusercontent.com/58020519/106418995-d074f880-649a-11eb-8e2e-14731e206a77.png)  

위 과정을 모두 수행하면  
![7-9](https://user-images.githubusercontent.com/58020519/106419000-d36fe900-649a-11eb-9a2d-6d66d8469fa0.png)  
해당 하는 키를 찾을 수 있다.
