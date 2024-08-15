###### Assignment 2 ###############
# coder: Howard Nguyen
# class: CS-4660-01
# date: 10/23/2023
###################################


import copy

########### Question 1 ########################

def BFS(FRINGE):
    queue = FRINGE
    resultStack = []

    while len(queue) > 0:
        curNode = queue.pop(0)
        if type(curNode) == list:
            for node in curNode:
                queue.append(node)
        else:
            resultStack.append(curNode)
    
    return resultStack


TREES = [
    [[["L", "E"], "F"], "T"],
    ["R", ["I", ["G", ["H", "T"]]]],
    [ ["A", ["B"]], ["D"], "C" ],
    ["T", ["H", "R", "E"], "E"],
    ["A", [["C", [[["E"]], "D"]], "B"]]
]

for tree in TREES:
    print( BFS(tree) )



########### Question 2 ########################
"""
invalid cases
dog <-> baby
baby <-> poison

State = {
    holding,
    start,
    goal
}

desired path
{DP} [B] {}
{D} [P] {B}
{D} [B] {P}
{B} [D] {P}
{B} [] {DP}
{} [B] {DP}
{} [] {BDP}
"""

def FINAL_STATE(S):
    # Fill in the logic for checking the goal state here
    return "B" in S["goal"] and "D" in S["goal"] and "P" in S["goal"]


def NEXT_STATE(S, A):
    # Fill in the logic for getting the next state after applying an operator
    copy_S = copy.deepcopy(S)

    for ele in copy_S["start"]:
        if ele == A:
            curHold = copy_S["holding"]
            copy_S["holding"] = ele
            if curHold is not None:
                copy_S["start"][ copy_S["start"].index(ele) ] = curHold
            else:
                copy_S["start"].remove(ele)
            return copy_S
    
    for ele in copy_S["goal"]:
            if ele == A:
                curHold = copy_S["holding"]
                copy_S["holding"] = ele
                if curHold is not None:
                    copy_S["goal"][ copy_S["goal"].index(ele) ] = curHold
                else:
                    copy_S["goal"].remove(ele)
                return copy_S



def SUCC_FN(S):
    # Fill in the logic for getting all possible legal successor states
    newStates = []

    # states swaping with starter
    if len(S["start"]) < 1:
        copy_S = copy.deepcopy(S)
        copy_S["start"].append( copy_S["holding"] )
        if not all(x in copy_S["start"] for x in ["B", "D"] ) and not all(x in copy_S["start"] for x in ["B", "P"] ):
            newStates.append(copy_S)
    else:
        for i in range(0, len(S["start"])):
            copy_S = copy.deepcopy(S)
            curHold = copy_S.get("holding")
            copy_S["holding"] = copy_S.get("start")[i]
            copy_S["start"][i] = curHold
            # skips illegal conditions
            if all(x in copy_S["start"] for x in ["B", "D"] ) or all(x in copy_S["start"] for x in ["B", "P"] ):
                continue
            # clean 'None' values
            while None in copy_S["start"]:
                copy_S["start"].pop( copy_S["start"].index(None) )
            if newStates.count(copy_S) < 1:
                newStates.append(copy_S)
    
    # states swaping with goal
    if len(S["goal"]) < 1:
        copy_S = copy.deepcopy(S)
        copy_S["goal"].append( copy_S["holding"] )
        if not all(x in copy_S["goal"] for x in ["B", "D"] ) and not all(x in copy_S["goal"] for x in ["B", "P"] ):
            newStates.append(copy_S)
    else:
        for i in range(len(S["goal"])):
            copy_S = copy.deepcopy(S)
            curHold = copy_S.get("holding")
            copy_S["holding"] = copy_S.get("goal")[i]
            copy_S["goal"][i] = curHold
            # skips illegal conditions
            if all(x in copy_S["goal"] for x in ["B", "D"] ) or all(x in copy_S["goal"] for x in ["B", "P"] ):
                continue
            # clean 'None' values
            while None in copy_S["start"]:
                copy_S["start"].pop( copy_S["start"].index(None) )
            if newStates.count(copy_S) < 1:
                newStates.append(copy_S)
    
    return newStates


def ON_PATH(S, STATES):
    # Check if S is in STATES
    return STATES.count(S) > 0

def MULT_DFS(STATES, PATH):
    # Fill in the logic for multi DFS
    pass


def DFS(S, PATH):
    # Fill in the logic for depth-first search
    stack = SUCC_FN(S)

    while len(stack) > 0:
        nextState = stack.pop()
        if ON_PATH(nextState, PATH) or nextState == S:
            continue
        if FINAL_STATE(nextState):
            PATH.append(nextState)
            return PATH
        PATH.append(nextState)
        stack = SUCC_FN(nextState)

    return PATH
    


iniState = {
    "holding": None,
    "start": ["B", "D", "P"],
    "goal": []
}

print( DFS(iniState, []) )
