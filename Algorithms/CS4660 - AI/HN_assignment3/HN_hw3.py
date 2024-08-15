from a_star import *
from enum import Enum
from copy import deepcopy
import math


class Tile(Enum):
    EMPTY = 0
    WALL = 1
    BOX = 2
    GOAL = 3
    KEEPER = 4
    BOX_ON_GOAL = 5
    KEEPER_ON_GOAL = 6

VISUAL_LIST = [
    ' ',
    '#',
    '$',
    '.',
    '@',
    '*',
    '+'
]

#===== goal_test ===========================
# Test whither the given state is the goal state
def goal_test(S):
    #find a tile that's a box
    for row in S:
        for tile in row:
            if (tile == Tile.BOX.value):
                return False
    return True

#===== next_states ========================
# Returns all next legal states
def next_states(S):
    #find keeper tile
    keeper_pos = find_keeper(S)
    
    new_states = []
    #generate a new state based on each directional movement
    for move_dir in [
        [0, 1], #right
        [0, -1], #left
        [-1, 0], #up
        [1, 0], #down
    ]:
        copy_S = deepcopy(S)
        next_keeper_pos = [keeper_pos[0] + move_dir[0], keeper_pos[1] + move_dir[1]]
        next_tile = copy_S[ next_keeper_pos[0] ][ next_keeper_pos[1] ]

        if next_tile == Tile.WALL.value:
            continue
        if next_tile == Tile.BOX.value or next_tile == Tile.BOX_ON_GOAL.value:
            #check for valid Box movement
            next_box_pos = [next_keeper_pos[0] + move_dir[0], next_keeper_pos[1] + move_dir[1]]
            next_box_tile = copy_S[next_box_pos[0]][next_box_pos[1]]
            if next_box_tile == Tile.WALL.value or next_box_tile == Tile.BOX.value or next_box_tile == Tile.BOX_ON_GOAL.value:
                continue
            #moving box
            if copy_S[next_box_pos[0]][next_box_pos[1]] == Tile.GOAL.value:
                copy_S[next_box_pos[0]][next_box_pos[1]] = Tile.BOX_ON_GOAL.value
            else:
                copy_S[next_box_pos[0]][next_box_pos[1]] = Tile.BOX.value
            #moving keeper
            if copy_S[ next_keeper_pos[0] ][ next_keeper_pos[1] ] == Tile.GOAL.value or copy_S[ next_keeper_pos[0] ][ next_keeper_pos[1] ] == Tile.BOX_ON_GOAL.value:
                copy_S[ next_keeper_pos[0] ][ next_keeper_pos[1] ] = Tile.KEEPER_ON_GOAL.value
            else:
                copy_S[ next_keeper_pos[0] ][ next_keeper_pos[1] ] = Tile.KEEPER.value
            #changing previous tiles
            copy_S[keeper_pos[0]][keeper_pos[1]] = Tile.GOAL.value if  copy_S[keeper_pos[0]][keeper_pos[1]] == Tile.KEEPER_ON_GOAL.value else Tile.EMPTY.value
            
        else:
            #changing previous tiles
            copy_S[keeper_pos[0]][keeper_pos[1]] = Tile.GOAL.value if  copy_S[keeper_pos[0]][keeper_pos[1]] == Tile.KEEPER_ON_GOAL.value else Tile.EMPTY.value
            copy_S[next_keeper_pos[0]][next_keeper_pos[1]] = Tile.KEEPER_ON_GOAL.value if copy_S[next_keeper_pos[0]][next_keeper_pos[1]] == Tile.GOAL.value else Tile.KEEPER.value
        new_states.append(copy_S)
    return new_states


#===== h1 =====================
"""
This heuristic is admissible as it would branch and eventually 
lead to a state where all boxes being on the goal. However, it is a heuristic that
does not provide good guidance as it only tracks wither a box is on a goal.
Insted, a heuristic that tracks the distance the boxes are from the goals should
give better guidance whither the currrent state is getting closer or not the goal state.
"""
#==============================
def h1(S, _dummy):
    count = 0
    for row in S:
        for tile in row:
            if tile == Tile.BOX.value:
                count += 1
    return count

#===== h402612259 ==================================
"""
This heuristic returns a value for the state where it
adds h1 with the distance of each boxes to the nearest goal
and adds the keeper's distance to the nearest box
"""
def h402612259(S, _dummy):
    total_h = h0(S) + h1(S, _dummy)
    keeper_pos = find_keeper(S)
    all_box_pos = find_tiles(Tile.BOX, S)
    all_goal_pos = find_tiles(Tile.GOAL, S)

    #check keeper distance from nearest boxes not on a goal
    closest_distance = 9999999999
    for pos in all_box_pos:
        temp = abs(math.sqrt( math.pow(pos[0] - keeper_pos[0], 2) + math.pow(pos[1] - keeper_pos[1], 2) ))
        if temp < closest_distance:
            closest_distance = temp
    if (closest_distance < 9999999999):
        total_h += closest_distance
    
    #check boxes distance from the nearest goal
    for box_pos in all_box_pos:
        closest_goal = 9999999999
        for goal_pos in all_goal_pos:
            temp = abs(math.sqrt( math.pow(goal_pos[0] - box_pos[0], 2) + math.pow(goal_pos[1] - box_pos[1], 2) ))
            if temp < closest_goal:
                closest_goal = temp
        if (closest_goal < 9999999999):
            total_h += closest_goal

    #total_h is h0 + h1 + (all box distance to nearest goal) + (keeper's distance to nearest box) (excludes boxes on goals)
    return int(math.floor(total_h))


def h0(S):
    return 0


#===== find_keeper =================
# returns the an array of indexes from the state
# where the keeper is positioned in
def find_keeper(S):
    keeper_pos = None
    for i in range(0, len(S)):
        for j in range(0, len(S[i])):
            if (S[i][j] == Tile.KEEPER.value or S[i][j] == Tile.KEEPER_ON_GOAL.value):
                keeper_pos = [i, j]
                return keeper_pos
    if keeper_pos == None:
        raise "Keeper Not found in State"

#===== find_tiles ===================
# Finds all 2d array indexes of passed in tile
def find_tiles(tile, S):
    all_tile_pos = []
    for r in range(0, len(S)):
        for t in range(0, len(S[r])):
            if tile.value == S[r][t]:
                all_tile_pos.append( [r, t] )

    return all_tile_pos



def print_state(S):
    print("\n")
    for row in S:
        for tile in row:
            print(VISUAL_LIST[tile], end="")
        print("", end="\n")



#===== execution ===========
start_state = [
    [1,1,1,1,1,1,1],
    [1,0,0,0,3,0,1],
    [1,0,2,1,0,1,1],
    [1,0,0,4,2,0,1],
    [1,0,0,0,0,0,1],
    [1,0,0,0,0,3,1],
    [1,1,1,1,1,1,1]
]

path = astar(start_state, goal_test, next_states, h1, h0)
print("///// using h1 ////////////////////////////////")
for s in path:
    print_state(s)


path = astar(start_state, goal_test, next_states, h402612259, h0)
print("///// using custom h //////////////////////////")
for s in path:
    print_state(s)







