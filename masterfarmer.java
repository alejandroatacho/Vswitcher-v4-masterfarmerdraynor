// int junk_seeds = {5104, 5097, 5308, 5097, 5309, 5318, 5322, 5098, 5306, 5324, 5102, 5305, 5310, 5320, 5096, 5307};
int foodId = 13441;
int foodId_count = v.getInventory().count(foodId);
WorldPoint wp1 = new WorldPoint(3092, 3245 , 0);
WorldPoint wp2 = new WorldPoint(3080, 3249 , 0);
WorldPoint currentLocation = client.getLocalPlayer().getWorldLocation();

if(client.getBoostedSkillLevel(Skill.HITPOINTS) <= 40) {
    if (foodId_count > 0) {
        v.getVars().setBoolean("HPMONITORING", true);
        v.getInventory().eat(foodId);
        v.getCallbacks().afterTicks(1, () -> {
          v.getVars().setBoolean("HPMONITORING", false);
        });
    } else {
        v.getWalking().walk(wp1);
    }
}
else if ((v.getInventory().inventoryFull() || foodId_count < 1) && currentLocation.equals(wp1) && !v.getWalking().isMoving())
{
    v.getBank().depositAll();
    v.getCallbacks().afterTicks(2, () -> {
        v.getBank().withdraw(foodId, 4);
        v.getCallbacks().afterTicks(3, () -> {
            if (v.getInventory().hasIn(foodId) && !v.getWalking().isMoving()) {
                v.getWalking().walk(wp2);
            }
        });
    });
}
else if (v.getInventory().inventoryFull() || foodId_count < 1)
{
    v.getWalking().walk(wp1);
}
else {
    v.invoke("Pickpocket","<col=ffff00>Master Farmer",1859,11,0,0,false);
}