# Guide
Gametick: 2 trigger
anti ban delay: 75, 150

## WARNING
- Make sure the food is visible in your bank currently :3, this ain't gonna scroll in there or look for it.

### Setup
1. Copy the provided Java code into your code editor.
2. Save the file with an appropriate name.
3. change the first line of code to your food id (the food your eating, this one is eating angler fish)

### Usage
1. Start the script in draynor village near master farmer.
2. Ensure you have the necessary items and equipment equipped. (rogue set *optional*
3. The script will perform the following actions based on the player's health and inventory:

   - If the player's current hitpoints level is 40 or lower:
     - If there is food (identified by the `foodId`) in the inventory, the script will:
       - Enable hitpoints monitoring.
       - Eat the food item.
       - Disable hitpoints monitoring after a short delay.
   - If the player's hitpoints level is above 40:
     - If the inventory is full or there is no food in the inventory the script will:
       - Deposit all items in the bank.
       - Withdraw the food items identified by `foodId`.

---

**Note**: The information provided above is for reference only. Please ensure that you understand the code and its implications before implementing it in Vswitcher.