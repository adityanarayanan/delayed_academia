#!/usr/bin/python3
from functional_sets import FuncSet, singleton_set, set_builder

# Unit tests for objects of type FuncSet defined in the module 'functional_sets'

def main():
    print('Unit tests for FuncSet API')
    print('Defining \'set1\' as set of integers between 1 and 9:')
    set1 = set_builder(1, 9)
    print('Contents of set1: ' + set1.tostring())
    print('Defining \'set2\' as set of integers between 5 and 13:')
    set2 = set_builder(5, 13)
    print('Contents of set2: ' + set2.tostring())
    print()
    print('Union of set1 and set2: ' + set1.union(set2).tostring())
    print('Intersection of set1 and set2: ' + set1.intersect(set2).tostring())
    print('Difference of set1 and set2: ' + set1.diff(set2).tostring())
    print('\nNow testing advanced functions...\n')
    print('Testing \'filter\' on set1 using predicate \'x such that x is less than 7 and greater than 3\': ' + set1.filter(lambda x: x < 7 and x > 3).tostring())
    print('Testing \'forall\' on set1 with predicate \'x such that x < 1\': ' + str(set1.forall(lambda x: x < 1)))
    print('Testing \'forall\' on set1 with predicate \'x such that x < 10\': ' + str(set1.forall(lambda x: x < 10)))
    print('Testing \'exists\' on set1 with predicate \'x such that x squared is 36\': ' + str(set1.exists(lambda x: x ** 2 == 36)))
    print('Testing \'exists\' on set1 with predicate \'x such that x < 0\': ' + str(set1.exists(lambda x: x < 0)))
    print('Mapping transformation \'x -> x squared\' on set1: ' + set1.map(lambda x: x ** 2).tostring())

if __name__ == '__main__':
    main()
