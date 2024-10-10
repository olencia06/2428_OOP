// Author: Olencia Fernandes
// Roll no: 2428
// Topic: Goa Legislative Assemby
// Start Date: 22 July 2024
// Modified Date: 22 july 2024
// Description: To identify all the OOP concepts in the Goa Legislative Assembly

using System;
using System.Collections.Generic;

namespace OOP.CSharp
{
    class MLA
    {
        public string Name { get; set; }
        public string Constituency { get; set; }
        public string Party { get; set; }

        public MLA(string name, string constituency, string party)
        {
            Name = name;
            Constituency = constituency;
            Party = party;
        }

        public override string ToString()
        {
            return $"MLA \nName={Name}\nConstituency={Constituency}\nParty={Party}\n";
        }
    }

    class ChiefMinister : MLA
    {
        public ChiefMinister(string name, string constituency, string party) : base(name, constituency, party) { }

        public override string ToString()
        {
            return $"Chief Minister \nName={Name}\nConstituency={Constituency}\nParty={Party}\n";
        }
    }

    class Bill
    {
        public string Title { get; }
        public string Type { get; }
        public MLA Mla { get; }
        public int Term { get; }

        public Bill(string title, string type, MLA mla, int term)
        {
            Title = title;
            Type = type;
            Mla = mla;
            Term = term;
        }

        public void DisplayInfo()
        {
            Console.WriteLine($"\nBill Title: {Title}\nType: {Type}\nMember: {Mla.Name}\nTerm: {Term}\n");
        }
    }

    class AssemblyManager
    {
        private List<MLA> mlas = new List<MLA>();
        private List<Bill> bills = new List<Bill>();

        public void AddMla(MLA mla)
        {
            mlas.Add(mla);
        }

        public void AddBill(Bill bill)
        {
            bills.Add(bill);
        }

        public void DisplayAllMlas()
        {
            foreach (var mla in mlas)
            {
                Console.WriteLine(mla);
            }
        }

        public void DisplayBillsByMla(string mlaName)
        {
            bool found = false;
            foreach (var bill in bills)
            {
                if (bill.Mla.Name.Equals(mlaName, StringComparison.OrdinalIgnoreCase))
                {
                    bill.DisplayInfo();
                    found = true;
                }
            }
            if (!found)
            {
                Console.WriteLine($"No bills found for member: {mlaName}");
            }
        }

        public void DisplayBillsByTerm(int term)
        {
            bool found = false;
            foreach (var bill in bills)
            {
                if (bill.Term == term)
                {
                    bill.DisplayInfo();
                    found = true;
                }
            }
            if (!found)
            {
                Console.WriteLine($"No bills found for term: {term}");
            }
        }
    }

    class GoaLegislativeAssembly
    {
        static void Main(string[] args)
        {
            var assemblyManager = new AssemblyManager();

            // Creating some sample members and bills
            var cm1 = new ChiefMinister("Pramod Sawant", "Sanquelim", "BJP");
            var mla1 = new MLA("Shri. Nilesh Cabral", "Curchorim", "BJP");
            var mla2 = new MLA("Shri. Mauvin Godinho", "", "BJP");
            assemblyManager.AddMla(cm1);
            assemblyManager.AddMla(mla1);
            assemblyManager.AddMla(mla2);

            var bill1 = new Bill("The Goa Education Development Corporation (Amendment) Bill", "Government Bill", cm1, 2024);
            var bill2 = new Bill("The Goa Change of Name and Surname (Amendment) Bill", "Government Bill", mla1, 2022);
            var bill3 = new Bill("The Goa School Education (Amendment) Bill", "Government Bill", cm1, 2023);
            assemblyManager.AddBill(bill1);
            assemblyManager.AddBill(bill2);
            assemblyManager.AddBill(bill3);

            // Displaying menu and processing user input
            bool exit = false;
            while (!exit)
            {
                Console.WriteLine("\nChoose an option:\n1. Display all bills made by a certain member\n2. Display all bills by term\n3. Council of Members\n4. Exit");

                int choice = int.Parse(Console.ReadLine());

                switch (choice)
                {
                    case 1:
                        Console.WriteLine("Enter member name:");
                        string memberName = Console.ReadLine();
                        assemblyManager.DisplayBillsByMla(memberName);
                        break;
                    case 2:
                        Console.WriteLine("Enter term:");
                        int term = int.Parse(Console.ReadLine());
                        assemblyManager.DisplayBillsByTerm(term);
                        break;
                    case 3:
                        assemblyManager.DisplayAllMlas();
                        break;
                    case 4:
                        exit = true;
                        Console.WriteLine("Exiting program...");
                        break;
                    default:
                        Console.WriteLine("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
        }
    }
}
