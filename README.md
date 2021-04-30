# 자동차 경주 게임
## 진행 방법
* 숫자 야구 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 과제를 제출한다.

## 과제 제출 과정
* [과제 제출 방법](https://github.com/next-step/nextstep-docs/tree/master/precourse)

---
## Functionality Requirements
- [ ] 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다. (RacingGame)
- [ ] 각 자동차에 이름을 부여할 수 있다. (RacingCar)
- [ ] 각 자동차 이름은 5자 이하만 가능하다. (RacingCar - Validation)
- [ ] 사용자는 몇 번의 이동을 할 것인지를 입력할 수 있어야 한다. (View, Controller)
- [ ] 전진하는 조건은 0에서 9 사이에서 random 값을 구한 후, random 값이 4 이상일 경우 전진하고, 3 이하일 경우 멈춘다. (RacingCar - Trial Validation)
- [ ] 자동차 경주 게임을 완료한 후 누가 우승했는지를 알려준다. 우승자는 한 명 이상일 수 있다. (RacingGame)

---
## Programming Requirements
1. Interface Segregation Principle (Method Separation)
    - [https://naver.github.io/hackday-conventions-java/](https://naver.github.io/hackday-conventions-java/)
    - Indent depth max: 1
    - Don't use "else" and "switch/case".
    - Method Code Line max : 10
    - Method has only one responsibility. (SRP)

2. Unit Test
    - Implement unit test for logic. (But not need for UI Layer.)
    - Logic Layer, UI Layer
    - Using JUnit5, AssertJ

3. Code Refactoring
    - Implementation by using First Class Collection
    - Wrapping all primitive and string values 
    
---
## Project Requirements
- [https://github.com/next-step/java-baseball-precourse](https://github.com/next-step/java-baseball-precourse)
- [AngularJS Commit Message Conventions](https://gist.github.com/stephenparish/9941e89d80e2bc58a153)
- [커밋 메시지 규약 정리 (the AngularJS commit conventions)](https://velog.io/@outstandingboy/Git-%EC%BB%A4%EB%B0%8B-%EB%A9%94%EC%8B%9C%EC%A7%80-%EA%B7%9C%EC%95%BD-%EC%A0%95%EB%A6%AC-the-AngularJS-commit-conventions)
