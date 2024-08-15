###### Assignment 1 ###############
# coder: Howard Nguyen
# class: CS-4660-01
# date: 10/16/2023
###################################

def PAD(n, use_sum=False):
    if n < 3: return 0 if use_sum else 1
    
    last = 1
    sec_last = 1
    cur_n = 2
    count = 0

    while n > 3:
        count += 1
        next = last + sec_last
        sec_last = last 
        last = cur_n
        cur_n = next
        n -= 1

    return count if use_sum else cur_n


def SUM(n):
    return PAD(n, True)




def ANON(tree):
    tree_list = []
    for node in tree:
        if type(node) == list:
            tree_list.append(ANON(node))
        else:
            tree_list.append('?')
    return tree_list





def main():
    #Task 1 & 2
    print("========= PAD and SUM ============")
    for i in range(2, 6):           # from 2 to 5
        print("\nn = " + str(i))
        print("PAD = " + str(PAD(i)))
        print("SUM = " + str(SUM(i)))

    #Task 3
    print("========= ANON Function ============")
    print( ANON([[["L", "E"], "F"], "T"]) )
    print( ANON(["\'", "FOO", "3.1", "-0.2"]) )
    print( ANON([ [["1", "2"], ["FOO", "3.1"]], ["BAR", "-0.2"] ]) )
    print( ANON(["FOO"]) )

main()