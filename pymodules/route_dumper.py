

import path_finder
import os
import json

# TODO: file which will do bfs between each pair of vertices and store the set of shortest paths between those vertices.
# these paths can be used for a quick lookup in realtime when a passenger sends a query.
# This json will be moved from local to a database so that it can be used in whichever environment we write our business logic in.


def load_adj():
    absolute_path = os.path.dirname(__file__)
    adj_path = os.path.join(absolute_path, '../data/amherst_routes.json')
    f = open(adj_path)
    return json.load(f)



def dump_paths(routes_dict):
    absolute_path = os.path.dirname(__file__)
    dump_path = os.path.join(absolute_path, '../data/amherst_routes_dump.json')
    print('dumping file now')
    with open(dump_path, "w") as f:
        json.dump(routes_dict,f)



if __name__ == "__main__":

    routes_dict = {}
    adj_list = load_adj()
    vertices = list(adj_list.keys())
    print('started')
    for u in vertices:
        for v in vertices:
            if u==v:
                continue
            try:
                paths = path_finder.generate_return_paths(u,v,adj_list)
                if u not in routes_dict:
                    routes_dict[u]=[]
                routes_dict[u].append({'dest':v,'paths':paths})
            except Exception as e:
                print(e)

    dump_paths(routes_dict)
    


 

    
    