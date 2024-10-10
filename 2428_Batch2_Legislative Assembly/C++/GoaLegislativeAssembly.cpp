// Author: Olencia Fernandes
// Roll no: 2428
// Topic: Goa Legislative Assembly
// Start Date: 22 July 2024
// Modified Date: 22 July 2024
// Description: To identify all the OOP concepts in the Goa Legislative Assembly

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <memory>
#include <limits>

class MLA
{
private:
    std::string name;
    std::string constituency;
    std::string party;

public:
    MLA(std::string name, std::string constituency, std::string party)
        : name(name), constituency(constituency), party(party) {}

    virtual ~MLA() = default;

    std::string getName() const { return name; }
    std::string getConstituency() const { return constituency; }
    std::string getParty() const { return party; }

    void setName(std::string name) { this->name = name; }
    void setConstituency(std::string constituency) { this->constituency = constituency; }
    void setParty(std::string party) { this->party = party; }

    virtual std::string toString() const
    {
        return "MLA \nName=" + name + "\nConstituency=" + constituency + "\nParty=" + party + "\n";
    }
};

class ChiefMinister : public MLA
{
public:
    ChiefMinister(std::string name, std::string constituency, std::string party)
        : MLA(name, constituency, party) {}

    std::string toString() const override
    {
        return "Chief Minister \nName=" + getName() + "\nConstituency=" + getConstituency() + "\nParty=" + getParty() + "\n";
    }
};

class Bill
{
private:
    std::string title;
    std::string type;
    std::shared_ptr<MLA> mla;
    int term;

public:
    Bill(std::string title, std::string type, std::shared_ptr<MLA> mla, int term)
        : title(title), type(type), mla(mla), term(term) {}

    std::string getTitle() const { return title; }
    std::string getType() const { return type; }
    std::shared_ptr<MLA> getMla() const { return mla; }
    int getTerm() const { return term; }

    void displayInfo() const
    {
        std::cout << "\nBill Title: " << title << "\nType: " << type << "\nMember: " << mla->getName() << "\nTerm: " << term << "\n"
                  << std::endl;
    }
};

class AssemblyManager
{
private:
    std::vector<std::shared_ptr<MLA>> mlas;
    std::vector<Bill> bills;

public:
    void addMla(std::shared_ptr<MLA> mla) { mlas.push_back(mla); }
    void addBill(Bill bill) { bills.push_back(bill); }

    void displayAllMlas() const
    {
        for (const auto &mla : mlas)
        {
            std::cout << mla->toString() << std::endl;
        }
    }

    void displayBillsByMla(const std::string &mlaName) const
    {
        bool found = false;
        for (const auto &bill : bills)
        {
            if (bill.getMla()->getName() == mlaName)
            {
                bill.displayInfo();
                found = true;
            }
        }
        if (!found)
        {
            std::cout << "No bills found for member: " << mlaName << std::endl;
        }
    }

    void displayBillsByTerm(int term) const
    {
        bool found = false;
        for (const auto &bill : bills)
        {
            if (bill.getTerm() == term)
            {
                bill.displayInfo();
                found = true;
            }
        }
        if (!found)
        {
            std::cout << "No bills found for term: " << term << std::endl;
        }
    }
};

int main()
{
    AssemblyManager assemblyManager;

    std::shared_ptr<MLA> cm1 = std::make_shared<ChiefMinister>("Pramod Sawant", "Sanquelim", "BJP");
    std::shared_ptr<MLA> mla1 = std::make_shared<MLA>("Shri. Nilesh Cabral", "Curchorim", "BJP");
    std::shared_ptr<MLA> mla2 = std::make_shared<MLA>("Shri. Mauvin Godinho", "", "BJP");

    assemblyManager.addMla(cm1);
    assemblyManager.addMla(mla1);
    assemblyManager.addMla(mla2);

    Bill bill1("The Goa Education Development Corporation (Amendment) Bill", "Government Bill", cm1, 2024);
    Bill bill2("The Goa Change of Name and Surname (Amendment) Bill", "Government Bill", mla1, 2022);
    Bill bill3("The Goa School Education (Amendment) Bill", "Government Bill", cm1, 2023);

    assemblyManager.addBill(bill1);
    assemblyManager.addBill(bill2);
    assemblyManager.addBill(bill3);

    bool exit = false;
    while (!exit)
    {
        std::cout << "\nChoose an option:\n1. Display all bills made by a certain member\n2. Display all bills by term\n3. Council of Members\n4. Exit\n";

        int choice;
        std::cin >> choice;
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        switch (choice)
        {
        case 1:
        {
            std::cout << "Enter member name:\n";
            std::string memberName;
            std::getline(std::cin, memberName);
            assemblyManager.displayBillsByMla(memberName);
            break;
        }
        case 2:
        {
            std::cout << "Enter term:\n";
            int term;
            std::cin >> term;
            assemblyManager.displayBillsByTerm(term);
            break;
        }
        case 3:
            assemblyManager.displayAllMlas();
            break;
        case 4:
            exit = true;
            std::cout << "Exiting program...\n";
            break;
        default:
            std::cout << "Invalid choice. Please enter a valid option.\n";
            break;
        }
    }

    return 0;
}
