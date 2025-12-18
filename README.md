# Лабораторная работа №3: Гостиница

**Группа:** `ПМ-2502`  
**Студент:** `Ртищев Егор Андреевич` <br>
**Вариант:** `18`

---
## Условие:  
Разработать программу для управления номерами в гостинице с учётом правил размещения, включая возможность проживания с животными в чётных номерах. Реализовать функции добавления и выселения гостей или семей, а также распределения номеров в соответствии с заданными условиями.

### Описание функционала
1. **Создание гостиницы**  
   Создаёт гостиницу с заданным количеством `n` номеров. Изначально все номера свободны.

2. **Вывод списка номеров**  
   Отображает два списка:
   - Свободные номера;
   - Занятые номера с именами постояльцев.

3. **Поселение человека в заданный номер**  
   Размещает человека с указанным именем в переданный номер, если номер свободен.

4. **Поселение человека в первый свободный номер**  
   Размещает человека с указанным именем в первый свободный номер. Если свободных номеров нет, действие не выполняется.

5. **Поселение группы людей**  
   Принимает массив имён и размещает людей в свободные номера. Если номеров недостаточно, размещаются только те, для кого номера нашлись. Возвращает количество размещённых людей.

6. **Поселение группы людей начиная с определённого номера**  
   Размещает людей из массива имён в свободные номера, начиная с указанного номера. Если номеров недостаточно, размещаются только те, для кого номера нашлись. Возвращает количество размещённых людей.

7. **Выселение человека из номера**  
   Убирает человека из указанного номера, освобождая номер.

8. **Поселение семьи в соседние номера**  
   Размещает семью в последовательные свободные номера. Если таких номеров недостаточно, семья не размещается. Возвращает `true`, если размещение удалось, и `false`, если нет.

9. **Выселение человека с учётом семьи**  
   Выселяет указанного человека. Если он является частью семьи, выселяются все члены семьи.

10. **Поселение человека с животным в указанный номер**  
    Размещает человека с животным в переданный номер, если это чётный номер и он свободен.

11. **Поселение группы людей с животными**  
    Размещает группу людей с животными в свободные чётные номера. Возвращает количество успешно размещённых людей.

12. **Поселение семьи с животными**  
    Размещает семью в соседние чётные номера, учитывая количество животных. Если размещение возможно, возвращает `true`, иначе `false`.

---

### Постановка задачи
Разработать класс `Hotel`, реализующий указанный функционал управления номерами, и вспомогательный класс `Guest` для хранения информации о госте (имя, наличие животного, идентификатор семьи). Обеспечить корректное взаимодействие методов класса.

### Входные и выходные данные
*Методы класса работают с передаваемыми параметрами и возвращают соответствующие значения. Входные данные зависят от конкретного вызываемого метода.*

### Алгоритм
1. **Класс `Guest`**: хранит информацию о госте.
2. **Класс `Hotel`**:
   - Инициализирует массив `Rooms` размером `n`.
   - Реализует методы согласно требованиям, проверяя условия занятости номера, чётности номера (для животных) и семейной принадлежности.

### Программа

#### Класс `Guest`
```java
class Guest {
    String name;
    boolean haspet;
    int family_idx;
    
    public Guest(String name, boolean haspet, int family_idx) {
        this.name = name;
        this.haspet = haspet;
        this.family_idx = family_idx;
    }

    public String getName() {
        return name;
    }

    public boolean getHaspet() {
        return haspet;
    }

    public int getFamily_idx() {
        return family_idx;
    }
}
```
### Класс Hotel
```java
import java.io.PrintStream;

public class Hotel {

    private Guest[] Rooms;
    private int numberOfRooms;

    public Hotel(int n){
        this.numberOfRooms = n;
        this.Rooms = new Guest[n];
    }
    public void printRoomList(){
        String s = "Свободные номера: ";
        for (int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] == null){
                s += Integer.toString(i + 1) + ", ";
            }
        }

        if (s.charAt(s.length() - 2) == ','){
            s = s.substring(0, s.length() - 2);
        }
        s += "\n" + "Занятые номера: ";

        for (int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] != null){
                s += Integer.toString(i + 1) + ", ";
            }
        }
        if (s.charAt(s.length() - 2) == ','){
            s = s.substring(0, s.length() - 2);
        }
        System.out.println(s);
    }

    public void addGuest(int n, Guest guest){
        if (n <= numberOfRooms){
            if (Rooms[n - 1] == null){
                this.Rooms[n - 1] = guest;
            }
        }
        else{
            System.out.println("Неверные данные!");
        }
    }

    public void addGuest(Guest guest){
        for (int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] == null){
                Rooms[i] = guest;
                break;
            }
        }
    }

    public int addGroupOfGuests(Guest[] guests){
        int count = 0;
        for (int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] == null && count < guests.length){
                Rooms[i] = guests[count];
                count++;
            }
        }
        return count;
    }

    public int addGroupOfGuests(Guest[] guests, int start_room){
        int count = 0;
        if (start_room <= numberOfRooms && start_room > 0){
            for (int i = start_room - 1; i < numberOfRooms; i++){
                if (Rooms[i] == null && count < guests.length){
                    Rooms[i] = guests[count];
                    count++;
                }
            }
            return count;
        }
        else{
            System.out.println("Неверные данные!");
            return -1;
        }
    }

    public void deleteGuest(int n){
        if (n <= numberOfRooms && n > 0){
            Rooms[n - 1] = null;
        }
        else{
            System.out.println("Неверные данные!");
        }
    }

    public int[] maxFreeConsecutiveRooms(){
        int[] res = new int[2];
        int maxLength = 0;
        int index = 0;
        int count = 0;
        for (int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] == null){
                count++;
            }
            else{
                count = 0;
            }
            if (count > maxLength){
                maxLength = count;
                index = i - maxLength + 1;
            }
        }
        res[0] = maxLength;
        res[1] = index;
        return res;
    }

    public boolean addFamily(Guest[] family){
        int FreeConsecutiveRooms = maxFreeConsecutiveRooms()[0];
        int indexMaxConsecutiveRooms = maxFreeConsecutiveRooms()[1];

        if (FreeConsecutiveRooms >= family.length){
            for (int i = indexMaxConsecutiveRooms; i < indexMaxConsecutiveRooms + family.length; i++){
                Rooms[i] = family[i - indexMaxConsecutiveRooms];
            }
            return true;
        }
        return false;
    }

    public void deleteGuestOrFamily(Guest guest){
        for(int i = 0; i < numberOfRooms; i++){
            if (Rooms[i] != null){
                if (Rooms[i].getFamily_idx() == guest.getFamily_idx()){
                    Rooms[i] = null;
                }   
            }
        }   
    }

    public void addGuestWithPet(int room_idx, Guest guest){
        if (room_idx <= numberOfRooms && room_idx > 0){
            if (room_idx % 2 == 0){
                Rooms[room_idx - 1] = guest;
            }
        }
        else{
            System.out.println("Неверные данные!");
        }
    }

    public int addGroupOfGuestsWithPets(Guest[] guests){
        int added_guests_counter = 0;

        for (int i = 1; i < numberOfRooms - 1; i += 2){
            if (added_guests_counter < guests.length && Rooms[i] == null){
                Rooms[i] = guests[added_guests_counter];
                added_guests_counter++;
            }
        }

        return added_guests_counter;
    }

    public int[] maxFreeEvenConsecutiveRooms(){
        int indexMaxFreeEvenConsecutiveRooms = -1;
        int maxFreeEvenConsecutiveRooms = 0;
        int count = 0;

        int[] res = new int[2];
        if (numberOfRooms > 1){
            if (numberOfRooms % 2 == 0){
                for (int i = 1; i < numberOfRooms; i += 2){
                    if (Rooms[i] == null){
                        count++;
                        if (maxFreeEvenConsecutiveRooms < count){
                            maxFreeEvenConsecutiveRooms = count;
                            indexMaxFreeEvenConsecutiveRooms = i - maxFreeEvenConsecutiveRooms * 2 + 2;
                        }
                    }
                    else{
                        count = 0;
                    }
                }
            }
            else{
                 for (int i = 1; i < numberOfRooms - 1; i += 2){
                    if (Rooms[i] == null){
                        count++;
                        if (maxFreeEvenConsecutiveRooms < count){
                            maxFreeEvenConsecutiveRooms = count;
                            indexMaxFreeEvenConsecutiveRooms = i - maxFreeEvenConsecutiveRooms * 2 + 2;
                        }
                    }
                    else{
                        count = 0;
                    }
                }               
            }
        }
        res[0] = maxFreeEvenConsecutiveRooms;
        res[1] = indexMaxFreeEvenConsecutiveRooms;

        return res;
    }
    public boolean addFamliyWithPets(Guest[] family, int numberOfPets){
        int maxFreeEvenConsecutiveRooms = maxFreeEvenConsecutiveRooms()[0];
        int indexFreeEvenMaxConsecutiveRooms = maxFreeEvenConsecutiveRooms()[1];
        int added_guests_counter = 0;

        if (numberOfPets > maxFreeEvenConsecutiveRooms){
            return false;
        }
        if (family.length > maxFreeEvenConsecutiveRooms){
            return false;
        }
        else{
            for (int i = indexFreeEvenMaxConsecutiveRooms; i < indexFreeEvenMaxConsecutiveRooms + family.length * 2; i += 2){
                Rooms[i] = family[added_guests_counter];
                added_guests_counter++;
            }
            return true;
        }
    }
}
