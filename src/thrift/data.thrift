namespace java com.test.thrift

typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person {
    1:optional String username,
    2:optional int age,
    3:optional boolean married
}

struct God {
    1:optional String name,
    2:optional String job,
    3:optional int age,
    4:optional list<Person> personArray
}

exception DataException {
    1:optional String message,
    2:optional String callStack,
    3:optional String date
}

service PersonService {
    Person getPersonByUsername(1:required String username) throws (1:DataException dataException),
    void savePerson(1:required Person person) throws (1:DataException dataException)
}

service GodService {
    God getGodBySky(1:required String name),
    void createGod(1:required God god),
    void createPersonByGod (1:required list<Person> peronArray)
}