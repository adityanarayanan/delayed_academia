#!/usr/bin/python3

# Returns a function predicate representing the 'contains' function of the
# corresponding set
def singleton_set(num):
    return lambda x: x == num

# Returns a set containing the range of numbers between 'lower' and 'upper' (inclusive)
def set_builder(lower, upper):
    if lower == upper:
        return FuncSet(singleton_set(upper))
    else:
        return FuncSet(singleton_set(lower)).union(set_builder(lower + 1, upper))

class FuncSet:
    BOUND = 100

    # Initialize set as empty if no argument is provided, else, initialize
    # based on provided characteristic function *hint* use singleton_set *hint*
    def __init__(self, char_func = lambda x: False):
        self.contains = char_func

    # Make each FuncSet object callable with a reference to 'contains'
    def __call__(self, num):
        return self.contains(num)

    # Returns the union of the two given sets i.e. the set of all elements
    # in either 'self' or 'other'
    def union(self, other):
        return FuncSet(lambda x: self.contains(x) or other.contains(x))

    # Returns the intersection of the two given sets i.e. the set of all
    # elements that are in both 'self' and 'other'
    def intersect(self, other):
        return FuncSet(lambda x: self.contains(x) and other.contains(x))

    # Returns the difference of 'self' and 'other', i.e. the elements of 'self'
    # that are not in 'other'
    def diff(self, other):
        return FuncSet(lambda x: self.contains(x) and (not other.contains(x)))

    # Returns the subset of 'self' for with the predicate 'predicate' holds
    def filter(self, predicate):
        return FuncSet(lambda x: self.contains(x) and predicate(x))

    # Returns whether all bounded integers within this set satisfy
    # the predicate 'predicate'
    def forall(self, predicate):
        def loop(index):
            if (not predicate(index)) and self.contains(index): return False
            elif index == self.__class__.BOUND: return True
            else: return loop(index + 1)

        return loop(-self.__class__.BOUND)

    # Returns whether there exists a bounded integer within this set that
    # satisfies the predicate 'predicate'
    def exists(self, predicate):
        return not self.forall(lambda x: not predicate(x))

    # Returns this set transformed by applying the function 'f' to each element
    def map(self, f):
        def loop(index):
            def set_to_pass(num):
                if self.contains(num): return FuncSet(singleton_set(f(num)))
                else: return FuncSet(lambda x: False) # empty set, always returns false

            if index > self.__class__.BOUND: return FuncSet(lambda x: False) # exit criterion
            else: return set_to_pass(index).union(loop(index + 1))

        return loop(-self.__class__.BOUND)

    # Display the contents of this set
    def tostring(self):
        contents = [num for num in range(-self.__class__.BOUND, self.__class__.BOUND) if self.contains(num)]
        content_string = '{ '
        for element in contents:
            if element == contents[-1]:
                content_string += str(element) + ' }'
            else:
                content_string += str(element) + ', '

        print(content_string)

# The end, so to speak

