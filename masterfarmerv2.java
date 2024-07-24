// int junk_seeds = {5104, 5097, 5308, 5097, 5309, 5318, 5322, 5098, 5306, 5324, 5102, 5305, 5310, 5320, 5096, 5307};
int foodId = 13441;
WorldPoint wp1 = new WorldPoint(3092, 3245 , 0);
WorldPoint wp2 = new WorldPoint(3080, 3249 , 0);

private void executeMainScript() {
    int foodId_count = v.getInventory().count(foodId);
    WorldPoint currentLocation = client.getLocalPlayer().getWorldLocation();

    if(shouldEat(foodId_count)) {
        eatFood(foodId_count);
    } else if(shouldGoToBank(foodId_count, currentLocation)) {
        goToBank(foodId_count);
    } else if(shouldWalkToBank(foodId_count)) {
        v.getWalking().walk(wp1);
    } else {
        pickpocketFarmer();
    }
}

private boolean shouldEat(int foodCount) {
    return client.getBoostedSkillLevel(Skill.HITPOINTS) <= 40 && foodCount > 0;
}

private void eatFood(int foodCount) {
    v.getVars().setBoolean("HPMONITORING", true);
    v.getInventory().eat(foodId);
    v.getCallbacks().afterTicks(1, () -> v.getVars().setBoolean("HPMONITORING", false));
    if(foodCount == 0) {
        v.getWalking().walk(wp1);
    }
}

private boolean shouldGoToBank(int foodCount, WorldPoint currentLocation) {
    return (v.getInventory().inventoryFull() || foodCount < 1) && currentLocation.equals(wp1) && !v.getWalking().isMoving();
}

private void goToBank(int foodId_count) {
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

private boolean shouldWalkToBank(int foodCount) {
    return v.getInventory().inventoryFull() || foodCount < 1;
}

private void pickpocketFarmer() {
    v.invoke("Pickpocket","<col=ffff00>Master Farmer",1859,11,0,0,false);
}
