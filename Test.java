import java.io.PrintStream;

public class Test {
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);
        Guest guest = new Guest("Alex", false, 0);
        Guest[] family_with_pets = new Guest[3];
        Guest[] groupOfGuests = new Guest[3];
        Guest[] groupOfGuests2 = new Guest[99];
        groupOfGuests[0] = new Guest("Ann", false, 1);
        groupOfGuests[1] = new Guest("Kate", false, 2);
        groupOfGuests[2] = new Guest("Ivan", false, 3);
        for (int i = 0; i < 99; i++){
            groupOfGuests2[i] = new Guest(" ", false, i + 4);
        }
        hotel.addGuest(guest);
        hotel.printRoomList();
        hotel.addGuest(5, guest);
        hotel.printRoomList();
        hotel.addGuest(11, guest);
        hotel.addGroupOfGuests(groupOfGuests);
        hotel.printRoomList();
        out.println(hotel.addGroupOfGuests(groupOfGuests2));
        hotel.printRoomList();
        for (int i = 1; i < 11; i++){
            hotel.deleteGuest(i);
        }
        hotel.printRoomList();

        hotel.addGroupOfGuests(groupOfGuests, 9);
        hotel.printRoomList();

        out.println(hotel.maxFreeConsecutiveRooms()[0]);
        out.println(hotel.maxFreeConsecutiveRooms()[1]);

        Guest[] family = new Guest[4];
        for (int i = 0; i < 4; i++){
            family[i] = new Guest("Johnson", false, 9);
        }
        hotel.addFamily(family);
        hotel.printRoomList();

        hotel.deleteGuestOrFamily(family[2]);
        hotel.printRoomList();

        Guest pet = new Guest(" ", true, 99);
        hotel.addGuestWithPet(2, pet);
        hotel.printRoomList();

        Guest[] pets = new Guest[3];
        for (int i = 0; i < 3; i++){
            pets[i] = new Guest(" ", true, 100 + i);
        }
        out.println(hotel.addGroupOfGuestsWithPets(pets));
        hotel.printRoomList();

        out.println(hotel.addGroupOfGuestsWithPets(pets));
        hotel.printRoomList();

        for (int i = 1; i < 11; i++){
            hotel.deleteGuest(i);
        }
        out.println(hotel.maxFreeEvenConsecutiveRooms()[0]);
        out.println(hotel.maxFreeEvenConsecutiveRooms()[1]);

        Guest[] familyWithPets = new Guest[6];
        for (int i = 0; i < 6; i++){
            familyWithPets[i] = new Guest("  ", true, 999);
        }
        Guest[] familyWithPets1 = new Guest[5];
        for (int i = 0; i < 5; i++){
            familyWithPets1[i] = new Guest("  ", true, 999);
        }
        hotel.addFamliyWithPets(familyWithPets, 6);
        hotel.printRoomList();

        hotel.addFamliyWithPets(familyWithPets1, 5);
        hotel.printRoomList();
    }
}

