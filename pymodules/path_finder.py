
from collections import deque
import os
import json
from sys import maxsize
from pprint import pprint


# modified bfs to store all shortest paths. 
def find_all_shortest_paths(src,dest,adj_list):
    num_nodes = len(adj_list)

    parent = {}
    for k in adj_list.keys():
        parent[k]= []
    # parent = [[] for _ in range(num_nodes)]
    dist = {}
    for k in adj_list.keys():
        dist[k] = maxsize


    q=deque()
    q.append(src)
    dist[src]=0
    parent[src]=[-1]

    while q:
        u = q[0]
        q.popleft()
        if u == dest:
            break   # destination found, no need to go farther.
        if adj_list[u]:
            for v in adj_list[u]:
                if dist[v] > dist[u]+1:

                    #update shortest distance from source node to current node.
                    dist[v]=dist[u]+1
                    q.append(v)
                    parent[v].append(u)
                elif dist[v]==dist[u]+1:
                    parent[v].append(u)

    # pprint(parent)
    return parent,dist


def accumulate_paths(parents,dest,paths,path):

    if dest == -1:
        #reached source.
        paths.append(path.copy())
        return

    for par in parents[dest]:
        path.append(dest)
        accumulate_paths(parents,par,paths,path)
        path.pop()
    



# simple dfs to find all paths won't work for large undirected graph.
# TODO: delete this method.
def find_path(u,v,adj_list,path,vis,all_paths,max_path_length = 1000000):

    if u in vis:
        return

    # print(u)    
    vis.add(u)
    path.append(u)

    if u == v:  #found destination.
        # print('path found')
        print(path)
        all_paths.append(path.copy())
    else:
        if u in adj_list:
            nei = adj_list[u]
            if nei:
                for n in nei:
                    find_path(n,v,adj_list,path,vis,all_paths)

    path.pop()
    vis.remove(u)

    

if __name__ == "__main__":
    
    use_short_graph=False

    absolute_path = os.path.dirname(__file__)
    if use_short_graph:
        adj_path = os.path.join(absolute_path, '../data/amherst_routes_short.json')
    else:
        adj_path = os.path.join(absolute_path, '../data/amherst_routes.json')
    
    f = open(adj_path)

    adj_list = json.load(f)
    # adj_list = {2:[0,1,6],1:[0,2,3],0:[2,3,1,4],3:[0,1,4],4:[0,3,7],6:[2,7],7:[6,4]}  #integer graph to test logic.
    # print(type(adj_list))

    # src = "BRITTANY MANOR DR"
    # dest = "VALLEY VIEW CIR"

    print("ENTER STARTING STREET ")
    src = input()
    print("ENTER DESTINATION STREET")
    dest = input()

    

    print("finding path from" + str(src) + " ---> " + str(dest))
    vis = set()
    all_paths=[]
    # find_path(src,dst,adj_list,[],vis,all_paths)
    parents,dist = find_all_shortest_paths(src,dest,adj_list)

    paths = []  # list of all shortest paths.
    path = [] # worker list to store current path.
    accumulate_paths(parents,dest,paths,path)

    print('printing all shortest paths')
    for path in paths:
        path.reverse()
        pprint(path)

    print('end')
    



