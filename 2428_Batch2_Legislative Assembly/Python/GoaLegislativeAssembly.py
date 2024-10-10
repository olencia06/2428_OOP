# Author: Olencia Fernandes
# Roll no: 2428
# Topic: Goa Legislative Assemby
# Start Date: 22 July 2024
# Modified Date: 22 july 2024
# Description: To identify all the OOP concepts in the Goa Legislative Assembly

class Member:
    def __init__(self, name, constituency):
        self.name = name
        self.constituency = constituency

    def get_name(self):
        return self.name

    def get_constituency(self):
        return self.constituency

    def display_info(self):
        print(f"\nName: {self.name}, \nConstituency: {self.constituency}")

class CouncilMember(Member):
    def __init__(self, name, constituency, position):
        super().__init__(name, constituency)
        self.position = position

    def get_position(self):
        return self.position

    def display_info(self):
        super().display_info()
        print(f"Position: {self.position}")

class Bill:
    def __init__(self, title, type, member, term):
        self.title = title
        self.type = type
        self.member = member
        self.term = term

    def get_title(self):
        return self.title

    def get_type(self):
        return self.type

    def get_member(self):
        return self.member

    def get_term(self):
        return self.term

    def display_info(self):
        print(f"\nBill Title: {self.title}\nType: {self.type}\nMember: {self.member.get_name()}\nTerm: {self.term}\n")

class AssemblyManager:
    def __init__(self):
        self.members = []
        self.bills = []

    def add_member(self, member):
        self.members.append(member)

    def add_bill(self, bill):
        self.bills.append(bill)

    def display_all_members(self):
        for member in self.members:
            member.display_info()

    def display_bills_by_member(self, member_name):
        found = False
        for bill in self.bills:
            if bill.get_member().get_name().lower() == member_name.lower():
                bill.display_info()
                found = True
        if not found:
            print(f"No bills found for member: {member_name}")

    def display_bills_by_term(self, term):
        found = False
        for bill in self.bills:
            if bill.get_term() == term:
                bill.display_info()
                found = True
        if not found:
            print(f"No bills found for term: {term}")

def main():
    assembly_manager = AssemblyManager()

  
    member1 = CouncilMember("Pramod Sawant", "Sanquelim", "Chief Minister")
    member2 = CouncilMember("Shri. Nilesh Cabral", "Curchorim", "MLA")
    member3 = CouncilMember("Shri. Mauvin Godinho", "", "Cabinet Minister")
    assembly_manager.add_member(member1)
    assembly_manager.add_member(member2)
    assembly_manager.add_member(member3)

    bill1 = Bill("The Goa Education Development Corporation (Amendment) Bill", "Government Bill", member1, 2024)
    bill2 = Bill("The Goa Change of Name and Surname (Amendment) Bill", "Government Bill", member2, 2022)
    bill3 = Bill("The Goa School Education (Amendment) Bill", "Building new roads.", member1, 2023)
    assembly_manager.add_bill(bill1)
    assembly_manager.add_bill(bill2)
    assembly_manager.add_bill(bill3)

    # Displaying menu and processing user input
    exit_program = False
    while not exit_program:
        print("\nChoose an option:\n1. Display all bills made by a certain member\n2. Display all bills by term\n3. Council of Members\n4. Exit")
        choice = int(input())

        if choice == 1:
            member_name = input("Enter member name: ")
            assembly_manager.display_bills_by_member(member_name)
        elif choice == 2:
            term = int(input("Enter term: "))
            assembly_manager.display_bills_by_term(term)
        elif choice == 3:
            assembly_manager.display_all_members()
        elif choice == 4:
            exit_program = True
            print("Exiting program...")
        else:
            print("Invalid choice. Please enter a valid option.")

if __name__ == "__main__":
    main()
